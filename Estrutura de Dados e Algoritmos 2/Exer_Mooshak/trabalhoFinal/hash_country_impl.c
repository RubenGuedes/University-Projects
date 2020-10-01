#include "hash_country.h"

HashNode *new_hash_node(char ct[COUNTRY], int corr, int aband, int term)
{
    HashNode *n = malloc(SIZEOF_HASHNODE);
    
    n->occupied = true;
    n->aband = aband;
    n->corr = corr;
    mystrcopy(ct, n->country);
    n->diplo = term;

    return n;
}

void destroy_hash_node(HashNode *n)
{
    free(n);
}

bool is_empty(HashNode *hn)
{
    return !hn->occupied;
}

void hnode_increase_active(HashNode *hn)
{
    hn->corr++;
}

void hnode_student_finish(HashNode *hn)
{
    hn->diplo++;
    hn->corr--;
}

void hnode_student_left(HashNode *hn)
{
    hn->aband++;
    hn->corr--;
}

void hnode_student_remove(HashNode *hn)
{
    hn->corr--;
}

HashCountry *new_hash_country()
{
    HashCountry *hc = malloc(SIZEOF_HASH);
    hc->ocupacao = 0;
     
    return hc;
}

HashCountry *disk_read_hash(FILE *fd_config)
{
  HashCountry *hc = new_hash_country();

  if (fseek(fd_config, 8, SEEK_SET) == 0)
    fread(hc, SIZEOF_HASH, 1, fd_config);
  
  return hc;
}

void disk_write_hash(FILE *fd_config, HashCountry *hc)
{
  if (fseek(fd_config, 8, SEEK_SET) == 0)
    fwrite(hc, SIZEOF_HASH, 1, fd_config);
}

void destroy_hash_country(HashCountry *hc)
{
    free(hc);
}

bool empty_hash(HashCountry *h)
{
    return h->ocupacao == 0;
}

bool full_hash(HashCountry *h)
{
    return h->ocupacao == TAMANHO_HASH;
}

int hash_insert(HashCountry *hc, HashNode *hn)
{
    if (!full_hash(hc))
    {
        int i = 0,
            hash_value;
        long node_hash = hashCode(COUNTRY, hn->country);

        do
        {
            hash_value = double_hashing(node_hash, i);

            if ( is_empty(&hc->h[hash_value]) )
            {
                hc->h[hash_value] = *hn;
                hc->ocupacao++;

                destroy_hash_node(hn);
                return hash_value;
            }
            i++;
        } while (i != TAMANHO_HASH);
    }
    return -1;
}

HashNode *hash_search(HashCountry *hc, char ct[COUNTRY])
{
    long hash_val = hashCode(COUNTRY, ct);
    int i = 0,
        hash_value = double_hashing(hash_val, i);
    HashNode *hn = NULL;
    do
    {
        hn = &hc->h[hash_value];
        if (!is_empty(hn) && compare(hn->country, ct) == 0)
            return hn;
        
        i++;    
        hash_value = double_hashing(hash_val, i);

    } while (!is_empty(&hc->h[hash_value]) && i < TAMANHO_HASH);
    
    return NULL;
}

int double_hashing(long key, int index)
{
    return (hash1(key) + index * hash2(key)) % TAMANHO_HASH;
}

int hash1(long key)
{
    return key % TAMANHO_HASH;
}

int hash2(long key)
{
    return 1 + (key % (TAMANHO_HASH - 1));
}
