import java.lang.Iterable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements Iterable<T>
{
	/*
	 *	Variables
	 * */
	private Doublenode<T>	header;
	private Doublenode<T>	footer;
	private int 			theSize;
	/*
	 *	Constructors
	 * */
	public DoubleLinkedList()
	{
		theSize = 0;
		header = new Doublenode<>();
		footer = new Doublenode<>();
		header.setNext( footer );
		footer.setPrevious(header);
	}
	/*
	 *	Methods
	 * */
	/*
	 *	iterator method
	 * */
	public Iterator<T> iterator()
	{
		return new DoubleLinkedListiterator<T>( header.getNext() );
	}
	/**
	 * 
	 */
	public ListIterator<T> backIterator()
	{
		return new DoubleLinkedListiterator<T>( footer.getPrevious() );
	}
	/**
	 * 
	 */
	public void add(T x)
	{
			// Ir para o nó anterior ao footer
			Doublenode<T> node_aux 		= footer.getPrevious();
			// criar no e ligar com o antecessor e o sucessor
			Doublenode<T> node_to_add 	= new Doublenode<>(node_aux, x, footer); 
			// Ligar ao nó criado o footer e o respetivo nó que lhe vai anteceder
			node_aux.setNext( 	node_to_add );
			footer.setPrevious( node_to_add );
			// Incrementar theSize
			theSize++;
	}
	/*
	 *	add(int index, T x) method
	 **/
	public void add(int index, T x)
	{
			Doublenode<T> node_aux = header.getNext();
			for (int i = 0; i < index; i++) 
			{			
				node_aux = node_aux.getNext();
			}
			// In the future will be before node_to_add
			Doublenode<T> previous_node = node_aux.getPrevious();
			// this node is between previous_node and further_node
			Doublenode<T> further_node 	= node_aux;
			// this node is after node_to_add
			Doublenode<T> node_to_add 	= new Doublenode<>(previous_node, x, further_node);
			// Create the remaining connections
			previous_node.setNext(	 node_to_add);
			further_node.setPrevious(node_to_add);
			// Incrementar theSize
			theSize++;
	}
	/*
	 * 	size method
	 * */
	public int size()
	{
			return theSize;
	}
	/*
	 *	isEmpty method
	 * */
	public boolean isEmpty()
	{
			return theSize == 0;
	}
	/*
	 *	clean method
	 * */
	public void clean()
	{
			header.setNext(		footer );
			footer.setPrevious( header );
			theSize = 0;
	}
	/*
	 *	set(int index, T x)
	 * */
	public void set(int index, T x)
	{
			Doublenode<T> node_aux = header.getNext();
			for (int i = 0 ; i < index; i++) 
			{
					node_aux = node_aux.getNext();
			}
			node_aux.setContent(x);
	}
	/*
	 *	get(int index)
	 * */
	public T get(int index)
	{
			Doublenode<T> node_aux = header.getNext();
			for (int i = 0 ;i < index ;i++ ) 
			{
				node_aux = node_aux.getNext(); 			
			}
			return node_aux.getContent();
	}
	/*
	 *	remove(int index)
	 * */
	public T remove(int index)
	{
			Doublenode<T> node_aux = header.getNext();
			for (int i = 0 ;i < index ;i++ ) 
			{
				node_aux = node_aux.getNext(); 			
			}
			// Get the content that we want to remove
			T cont = node_aux.getContent();
			// work with the node before the node that we want to remove
			node_aux = node_aux.getPrevious();
			// node further
			Doublenode<T> further_node = node_aux.getNext().getNext();
			// Create Links
			node_aux.setNext(further_node);
			further_node.setPrevious(node_aux);
			// Decrement theSize
			theSize--;
		
			return cont;
	}
	/*
	 *	remove(T x)
	 * */
	public T remove(T x)
	{
			Doublenode<T> node_aux = header.getNext();
			while( !node_aux.getContent().equals(x) )
			{
				node_aux = node_aux.getNext();
			}
			// store content to a variable
			T get_content = node_aux.getContent();
			// store previous node to this variable
			Doublenode<T> previous_node = node_aux.getPrevious();
			// Store further node to, a posteriori, create a link between them
			Doublenode<T> further_node  = node_aux.getNext();
			// Create Links
			previous_node.setNext(	 further_node);
			further_node.setPrevious(previous_node);
			// Decrement theSize
			theSize--;
			return get_content;
	}
	/*
	 *	toString method
	 * */
	public String toString()
	{
			String string = "";
			for (T st : this ) 
			{
				string += st + " ";	
			}
			return string;
	}
	/**********************************************************************
	 **********************************************************************
	 **********************************************************************
	 * 							OTHER CLASSES
	 **********************************************************************
	 **********************************************************************
	 **********************************************************************
	 */
	/**
	 * Class DoubleNode
	 */
	public class Doublenode<T>
	{
			/*
			 *	Variables
			 * */
			private T				content;
			private Doublenode<T> 	previous;
			private Doublenode<T> 	next;
			/* *
			 *	Constructors
			 * */
			public Doublenode()
			{
				this(null, null, null);
			}
			public Doublenode(T content)
			{
				this(null, content, null);
			}
			public Doublenode(Doublenode<T> previous, T content)
			{
				this(previous, content, null);
			}
			public Doublenode(Doublenode<T>	previous, T	content, Doublenode<T> next )
			{
				this.previous 	= previous;
				this.content 	= content;
				this.next		= next;
			}
			/*
			 *	Methods
			 * */
			// get e set previous
			public Doublenode<T> getPrevious()
			{
				return previous;
			}
			public void setPrevious(Doublenode<T> previous)
			{
				this.previous = previous;
			}
			// get e set content
			public T getContent()
			{
				return content;
			}
			public void setContent(T content)
			{
				this.content = content;
			}
			// get e set next
			public Doublenode<T> getNext()
			{
				return next;
			}
			public void setNext(Doublenode<T> next)
			{
				this.next = next;
			}
	}
	/**
	 * Class DoubleLinkedListIterator
	 */
	public class DoubleLinkedListiterator<T> implements ListIterator<T>
	{
			/*
			 *	Variable
			 * */
			private Doublenode<T> node;
			/*
			 * 	Constructor
			 * */
			public DoubleLinkedListiterator(Doublenode<T> node)
			{
				this.node = node;
			}
			/*
			 *	Methods
			 * */
			////////////////////////////////////////////////////////////////////
			/**
			 * 						  OVERRIDE METHODS
			 */
			@Override
			public void add(T e){}
			@Override
			public void set(T e){}
			@Override
			public void remove(){}
			@Override
			public int previousIndex(){ return -1; }
			@Override
			public int nextIndex(){  return -1;	}
			///////////////////////////////////////////////////////////////////
			/*
			 *	hasNext method
			 * */
			public boolean hasNext()
			{
				return node.getNext() != null;
			}
			/**
			 * hasPrevious method
			 */
			public boolean hasPrevious()
			{
				return node.getPrevious() != null;
			}
			/**
			 * previous method
			 */
			public T previous()
			{
				if( !hasPrevious() )
					throw new NoSuchElementException();
			
				T content 	= node.getContent();
				node 		= node.getPrevious();
			
				return content;
			}
			/*
			 *	next method
			 * */
			public T next()
			{
				if( !hasNext() )
					throw new NoSuchElementException();
			
				T content 	= node.getContent();
				node 		= node.getNext();	
			
				return content;
			}
	}
}