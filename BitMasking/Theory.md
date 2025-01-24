Alright, so lets start by trying to solve the above problem and we'll learn the concept through that. The question essentially boils down to Finding the Power Set of a given Set. The set here may contain multiple elements. The term 'Multiset' is more appropriate...but well, lets just stick to set.

First of all, we Claim that :

"If we list down all the binary numbers from 0 to (2^n)-1 , we get ALL the possible combinations of selecting n items"

Lets verify for n=3

000: None of the values in the set chosen

001: 1st chosen, 2nd and 3rd items left out

010: 2nd chosen, 1st and 3rd items left out

011: 1st and 2rd item chosen, 3rd one left out.

...

111: All 3 items chosen

This way, we have listed the 2^n ways of obtaining all the subsets from a set of n numbers. Unique or not, does not matter because the index of every element we are dealing with is unique.

Now for the computation part, the core idea is to brute force through every bit of every number from 0-2^n-1 and check for the set bits of each number.

Algorithm:

Run a loop for 'i' for all numbers from 0 to 2^(n-1).
When inside this loop, run a loop for 'j' from 0 to n-1 inclusive
Inside this loop, check if the 'j'th bit is SET(equal to 1) for the number 'i'.
If it is, then we include this element in our 'i'th subset
Done.
I was very confused when I learnt this the first time, so let me demonstrate with a small example: say for i=3, the binary representation is 011. When we run the inner loop from 1 to n for i which is currently 3, here's what we are actially doing.

Is the right most (LSB) bit set? Yes. So Include it. [01x]
Is the middle bit set? Yes. So Include it. [0x1]
Is the last (MSB) bit set? No. So Leave it out. [x11]
What we are essentially doing is that For every iteration of 'j', we are 'masking' all the bits in 'i' except for the bit at the 'j'th position. Hence the name BitMasking.

We use the BitWise '<<' operator for shifting the bit to be checked each time in j. (We are taking the BitWise '&' operation of 1 and x. If it is 1, it is SET since in boolean algebra, only 1.1=1)