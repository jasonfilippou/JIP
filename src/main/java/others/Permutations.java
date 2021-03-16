package others;
public class Permutations
{

	// App entry point
	public static void main(String[] args)
	{
		final String[] strings = {"ABC", "CARL", "JASON"};
		for (String s : strings)
		{
			System.out.println("Permutations for " + s + ":" );
			permutations(s.toCharArray(), 0 ,s.length() - 1);
		}
	}

	// Strings are immutable, so we need to represent the string as a (mutable) char array.
	private static void permutations(final char[] string, final int l, final int r)
	{
		if(l == r)
		{
			System.out.println(string);
		}
		else
		{
			// The index variable i decides which character to  swap with string[l] next.
			// When i = l, it will effectively not swap at all, which leads us to a left path
			// in the tree. Left paths maintain the current string, so this makes sense.
			for(int i = l; i <= r; i++)
			{
				swap(string, i, l);
				permutations(string, l + 1, r);     // Re-run the process for string[l+1,....r]
				swap(string, i, l);
			}
		}
	}

	private static void swap(final char[] array, final int i, final int j)
	{
		final char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
