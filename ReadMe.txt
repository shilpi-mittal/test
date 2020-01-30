To execute :
Execute the jar using 'java -jar test_task.jar'
You will be asked to enter amount, enter the input amount and press ENTER.

Assumptions :
1. output when zero cent , like 1.0= one DOLLAR EVEN
2. Output when zero dollar, like 0.01 = one cent
3. Output for zero, 0.0 = EVEN

Approach:
Take console input and convert it into big decimal to check if input is valid
1. convert input into string and then splitting at "."
2. if dollar value is not zero :
      3.  if number of digits in dollar value is not multiple of 3, then append zeros in beginning
      4. loop around dollar value string and get groups of 3 digits
      5. for each group:
          6. if hundredth place in group is not 0, convert into words (by picking word from "ones" as per value at hundreds place) and append into string builder
          7. convert tens-ones place into words and append into string builder
      8. convert group position into words  (by picking word from "groups") and append into string builder
      9. append spaces and ","
      10. append "DOLLAR"/"DOLLARS" as per the dollar value
11. if decimal value is not zero:
      12. if number of digits is one, append zero
      13. convert into words and append
      14. append "CENT"/"CENTS" as per cent value
15. if decimal zero, append "EVEN"
16. to convert tens-ones place into words:
      17. if tens place is "1", pick word from "tensSeries" as per value at ones place
      18. else pick work from "tens" as per value at tens place and pick word from "ones" as per value at ones place

If given more time :
1. Handle inputs with more than 2 digits of decimals, like 1.231, currently it is rounding off.
2. explore better ways to check if input is validate