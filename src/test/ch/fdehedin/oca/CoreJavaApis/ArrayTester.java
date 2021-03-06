package ch.fdehedin.oca.CoreJavaApis;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsArrayWithSize;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.fdehedin.util.TestUtil;

/**
 * @author juiceterry
 *
 */
public class ArrayTester {

	private static final Logger log = Logger.getLogger(StringMethodTester.class.getSimpleName());

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	int[] arr1[];// 2 dimension
	int[][] arr2;

	@Test
	public void initTests() {
		int[] arr1[] = null;// 2 dimension
		int[][] arr2 = null;
		int[] arr3 = null;

		assertThat(arr1 == arr2, is(true));

		// DOES NOT COMPILE (cannot compare 2dim and 1 dim array...
		// assertThat(arr1==arr3, is(true));

		int[] arr4 = new int[] { 1, 2, 3 };
		int[] arr5 = new int[] { 1, 2, 3 };

		int[] arr6[] = new int[2][];
		int[] arr7 = { 1, 2, 3 };

		exception.expect(NegativeArraySizeException.class);
		int[] arr8[] = new int[-12][];
	}

	@Test
	public void chainer() {
		assertThat("hello".concat(" world").trim().equals("hello world"), is(true));

	}

	@Test
	public void arrayClassTester() {
		int[] array = new int[2];
		assertThat(array.getClass().isArray(), is(true));

		assertThat(array.length, is(2));

		assertThat(array.getClass().isPrimitive(), is(false));
		int i = 0;

		assertThat(Integer.valueOf(i).getClass().isPrimitive(), is(false));
	}

	@Test
	public void loopTester() {

		for (;; new String("")) {
			break;
		}

		int INT1 = 1;
		int INT2 = 2;

		for (int i = INT1; i < INT2; i++) {
			System.out.println(i);
		}

		int i = 0;

		String s = null;
		OUTHER: for (;; ++i) {
			int y = 0;
			for (; y < 20; y++) {
				System.out.println("i: " + i + "  y:" + y);
				if (y == 10) {
					break OUTHER;
				}
			}
		}
	}

	@Test
	public void initTester() {
		Object[] obj = new Object[2];
		obj[0] = Integer.valueOf("20");

	}

	@Test
	public void initWithStatement() {
		int i = 10;
		int arr[] = new int[i = 3];
		assertThat(arr.length, is(3));

	}

	@Test
	public void basicTests() {
		
	
		
		int[] numbers = new int[3]; // 3 elements
		assertThat(numbers[2], is(0));

		int[] numbers2 = { 23, 55, 87 };
		assertThat(numbers2.length, is(3));

		exception.expect(IndexOutOfBoundsException.class);
		assertThat(numbers[3], is(0)); // 0, 1, 2 = 3 elements...
	}

	@Test
	public void negativeAccessTests() {
		int[] numbers = new int[] { 1, 2, 3 }; // 3 elements
		exception.expect(ArrayIndexOutOfBoundsException.class);
		log.info("test: " + numbers[-1]);
	}

	@Test
	public void arrayLengthTest() {
		int[] arr = new int[0];
		assertThat(arr == null, is(false));

		String[] str = new String[0];
		assertThat(str == null, is(false));
	}

	@Test
	public void arrayInstantiator() {

		// all of those work even when there is space between brackets and type
		// definition..
		int[] arr1[] = new int[2][];
		int arr3[];

		int[] arr4, arr5; // 2 arrays of int..
		int arr6[], arr7; // 1 int array and 1 normal int..

		String[] bugs = { "cricket", "beetle", "ladybug" };
		String alias[] = bugs;

		assertThat(bugs == alias, is(true)); // 0, 1, 2 = 3 elements...

		assertThat(bugs.equals(alias), is(true)); // 0, 1, 2 = 3 elements...

		log.info(Arrays.toString(alias)); // pretty pring

		String birds[] = new String[6];
		assertThat(birds.length, is(6)); // 6, even though 6x null..

		// doesn't compile, if it is compiled like this, we need to provide the
		// size of the first dimension
		// String java[][] = new String[][];

		// declarations with size must be in the creation, right hand side..
		// String java[2][] = new String[3][];

		String[] java[] = new String[2][]; // works...
	}

	@Test
	public void testArrayExceptions() {
		int[] numbers = new int[10];

		exception.expect(ArrayIndexOutOfBoundsException.class);
		for (int i = 0; i <= numbers.length; i++)
			numbers[i] = i;

		exception.expect(ArrayIndexOutOfBoundsException.class);
		numbers[10] = 5; // throws exception.. 9 is the upper most bound!

		exception.expect(ArrayIndexOutOfBoundsException.class);
		numbers[numbers.length] = 5; // throws exception.. 9 is the upper most
										// bound!
	}

	@Test
	public void testSorting() {
		Integer numbers[] = { 3, 2, 1 };

		Arrays.sort(numbers);

		List<Integer> lst = Arrays.asList(numbers);
		assertThat(lst, TestUtil.isInAscendingOrdering());

	}

	@Test
	public void testSearching() {
		int numbers[] = { 3, 2, 1, 5, 8, 9 };
		Arrays.sort(numbers);

		int result;

		result = Arrays.binarySearch(numbers, 2);
		assertThat(result, is(1));

		result = Arrays.binarySearch(numbers, 4);
		assertThat(result, is(-4));// 1, 2, 3, 44444, 5 = -4 --> like before pos
									// 5!!

		int numbersSort[] = { 2, 4, 6, 8 };
		result = Arrays.binarySearch(numbersSort, 4);
		assertThat(result, is(1));

		result = Arrays.binarySearch(numbersSort, 1);
		assertThat(result, is(-1));

		result = Arrays.binarySearch(numbersSort, 3);
		assertThat(result, is(-2));
	}

	@Test
	public void testVarArgs() {

		this.testVarArgsSingle("super", "bla", "super", "test");
		this.testVarArgsSingle("Nur 1 param...");

	}

	private void testVarArgsSingle(String... args) {
		assertThat(args, arrayWithSize(isOneOf(1, 4)));
	}

	@Test
	public void compareArrays() {
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 1, 2, 3 };

		// not the same object.
		assertThat(arr1 == arr2, is(false));
		// not the same object.
		assertThat(arr1.equals(arr2), is(false));

		arr1[0] = arr1[1] = 0;

		System.out.println("arr1[0]: " + arr1[0]);
		System.out.println("arr1[1]: " + arr1[1]);

	}

	@Test
	public void arrayLooper() {
		int size = 10;
		int i1 = 2;
		size = i1 = 1;

		int[] arr = new int[size];
		int i;
		for (i = 0; i < size; ++i) {

			System.out.println("i: " + i);

			System.out.println(arr[i]);
		}

		System.out.println("i after loop: " + i);
	}

}
