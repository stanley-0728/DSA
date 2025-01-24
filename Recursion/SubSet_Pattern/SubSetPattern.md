What is Subset Pattern?

Subset Pattern is a pattern that occurs when we have to find all the subsets of a set.
The pattern of taking some elements and removing them from the set is called subset pattern.

For example, if we have a set A = {1,2,3}, then the subsets are {∅,{1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}}.

The subsets of a set are the possible choices that we can make from the set. For example, if we have a set A = {1,2,3}, then the subsets are {∅,{1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}}.


Let us deep dive into the Subset Pattern.

In the above example, we have a set A = {1,2,3}. We need to find all the subsets of A.

Let us consider the following example:

A = {1,2,3}

The subsets of A are:

∅

{1}

{2}

{3}

{1,2}

{1,3}

{2,3}

{1,2,3}

We can see that the subsets of A are {∅,{1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}}  .
How did we find this?

Well, we can think of this in two ways:

  A= { 1, 2, 3 } 
  now i say that i have taken the first element of the set i.e. { 1 } and removed it from the set.
  Now i have to find all the subsets of the remaining set i.e. { 2, 3 }.
  Similarly, i can find all the subsets of { 2, 3 } and so on.  

Now, let us see the code:

public class SubsetPattern {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        findSubsets(arr, 0, arr.length - 1);
    }

    public static void findSubsets(int[] arr, int start, int end) {
        if (start > end) {
            System.out.println("{" + arr[start] + "}");
            return;
        }
        findSubsets(arr, start + 1, end);
        findSubsets(arr, start, end - 1);
    }
}

Output:

{}

{1}

{2}

{3}

{1,2}

{1,3}

{2,3}

{1,2,3}