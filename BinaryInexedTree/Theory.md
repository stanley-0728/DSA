Binary Indexed Tree also called Fenwick Tree provides a way to represent an array of numbers in an array, allowing prefix sums to be calculated efficiently. For example, an array is [2, 3, -1, 0, 6] the length 3 prefix [2, 3, -1] with sum (2 + 3 + -1 = 4). Calculating prefix sums efficiently is useful in various scenarios. Let's start with a simple problem.

We are given an array a[], and we want to be able to perform two types of operations on it.

Change the value stored at an index i. (This is called a point update operation)
Find the sum of a prefix of length k. (This is called a range sum query)
A straightforward implementation of the above would look like this.

int a[] = {2, 1, 4, 6, -1, 5, -32, 0, 1};
void update(int i, int v)   //assigns value v to a[i]
{
    a[i] = v;   
}
int prefixsum(int k)    //calculate the sum of all a[i] such that 0 <= i < k
{
   int sum = 0;
   for(int i = 0; i < k; i++)
        sum += a[i];
   return sum;
}
This is a perfect solution, but unfortunately the time required to calculate a prefix sum is proportional to the length of the array, so this will usually time out when large number of such intermingled operations are performed.

Can we do better than this? Off course. One efficient solution is to use segment tree that can perform both operation in O(logN) time.

Using binary Indexed tree also, we can perform both the tasks in O(logN) time. But then why learn another data structure when segment tree can do the work for us. It’s because binary indexed trees require less space and are very easy to implement during programming contests (the total code is not more than 8-10 lines).


When to use Binary Indexed Tree?

Before going for Binary Indexed tree to perform operations over range, one must confirm that the operation or the function is:

Associative. i.e f(f(a, b), c) = f(a, f(b, c)) this is true even for seg-tree

Has an inverse. eg:

addition has inverse subtraction (this example we have discussed)

Multiplication has inverse division

gcd() has no inverse, so we can’t use BIT to calculate range gcd’s

sum of matrices has inverse

product of matrices would have inverse if it is given that matrices are degenerate i.e. determinant of any matrix is not equal to 0

Space Complexity: O(N) for declaring another array of size N

Time Complexity: O(logN) for each operation(update and query as well)

Applications:

Binary Indexed trees are used to implement the arithmetic coding algorithm. Development of operations it supports were primarily motivated by use in that case.

Binary Indexed Tree can be used to count inversions in an array in O(N*logN) time.