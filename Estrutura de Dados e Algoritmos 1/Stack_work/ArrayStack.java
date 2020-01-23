import java.util.EmptyStackException;
import java.lang.RuntimeException;

public class ArrayStack<E> implements Stack<E>
{
	private int pos;
	private E[] arr;

	public ArrayStack()
	{
		this(10);
	}

	public ArrayStack(int comprimento)
	{
		pos = -1;
		arr = (E[]) new Object[comprimento];
	}

	public void push(E o) throws RuntimeException
	{
		if( (arr.length-1) == pos ){ 
			throw new RuntimeException("Overflow!");
		}
		else{
			arr[++pos] = o;
		}
	} 

	public E top() throws EmptyStackException
	{
		if ( empty() ){
			 throw new EmptyStackException();
		}

		return arr[pos];
	}

	public E pop() throws EmptyStackException
	{
		if ( empty() )
		{
			throw new RuntimeException("Empty Stack");
		}

		E val = top();
		arr[pos--] = null;
		return val;
	}

	public int size()
	{
		int posic = pos + 1;
		return posic;
	}

	public boolean empty()
	{
		return pos == -1;
	}

	public String toString()
	{
		String st = "";
		for (int i = 0; i < arr.length ;i++) 
		{
				st += "" + arr[i] + " "; 
		}
		return st;
	}
}