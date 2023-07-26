The polynomial derivative calculator written Java 11 and tested using the JUnit testing framework, allows users to input 
a polynomial expression and the order of the derivative they wish to compute. The program pareses the expression, calculates 
the derivative, and displays the result. The program supports polynomials with non-integer coefficients and exponents, negative
coefficients and exponents, and fraction, decimal, or integer coefficients. Therefore, allowing users to calculate higher order 
derivatives of expressions which are manually difficult to compute and use the program to check the results of their manual 
computations against the program's displayed result. The implementation uses the Strategy design pattern to handle different 
types of coefficients (integer, decimal, or fractions) and the Iterator design pattern to iterate through the polynomial. 

Given the vast applications of the program, researchers working on software applications that involve polynomials can integrate
the program as a feature, providing users with the ability to compute derivatives within their application. Additionally, 
the program serves as an educational tool for students learning about single variable derivatives as they can input expressions 
and compare the calculated derivative with their manual calculations, helping students identify areas they struggle with 
promoting active learning and problem-solving skills. 

Much of the code smells in earlier implementations of the program (i.e. Long Methods, Primitive Obsession, Lack of Unit Tests, etc...)
were fixed later onn, there still exists a Lack of Error Handling code smell which when fixed using separate methods would allow
for more robust and user-friendly code but could inadvertently introduce a Long Method code smell. Although, the implementation 
doesn't strictly follow Clean Architecture since it does not use Entities, Interfaces, Adapters, etc..., the implementation 
displays the Dependency Rule and modularity properly. Furthermore, the implementation adheres well with SOLID principles due
to the modularity and organization of the program allowing for easy modification and extension when desired. 
