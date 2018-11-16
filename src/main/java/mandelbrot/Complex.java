package mandelbrot;

import java.util.Objects;

/**
 * A class to represent complex numbers and their arithmetic.
 * Complex numbers are immutable.
 */
public class Complex {

    /**
     * The real part of a complex number.
     */
    final double real;

    /**
     * The imaginary part of a complex number.
     */
    final double imaginary;

    // @param imaginary imaginary component

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Zero as a complex number
     */
    static Complex ZERO = new Complex(0.0, 0.0);

    /**
     * One as a complex number
     */
    static Complex ONE = new Complex(1, 0);


    /**
     * The complex number whose square is -1
     */
    static Complex I = new Complex(0, 1);

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    static Complex rotation(double radians) {
        return new Complex(Math.cos(radians), Math.sin(radians));
    }

    // @return the complex <code>real + 0 i</code>

    public static Complex real(double real) {
        return new Complex(real, 0);
    }

    // @return the complex {@code this + addend}

    public Complex add(Complex addend) {
        return new Complex(this.real + addend.real,
                this.imaginary + addend.imaginary);
    }

    // @return A complex <code>c</code> such that <code>this + c = 0</code>

    Complex negate() {
        return new Complex(-this.real, -this.imaginary);
    }

    // @return A complex <code>c</code> such that <code>this * c = ||this|| ** 2</code>

    Complex conjugate() {
        return new Complex(this.real, -this.imaginary);
    }


    //@param subtrahend the complex to be subtracted from <code>this</code>
    //@return the complex number <code>this - subtrahend</code>

    Complex subtract(Complex subtrahend) {
        return new Complex( this.real - subtrahend.real,this.imaginary - subtrahend.imaginary);
    }

    //@param factor the complex number to multiply to <code>this</code>
    //@return the complex number {@code this * factor}

    Complex multiply(Complex factor) {
        return new Complex(
                this.real * factor.real - this.imaginary * factor.imaginary,
                this.real * factor.imaginary + this.imaginary * factor.real
        );
    }

    // @return <code>||this|| ** 2</code>

    double squaredModulus() {
        return (real * real) + (imaginary * imaginary);
    }

    //@return <code>||this||</code>

    double modulus() {
        return Math.sqrt(squaredModulus());
    }

    //@return a complex number <code>c</code> such that <code>this * c = 1</code>

    Complex reciprocal() {
        if(this.equals(ZERO)){
            throw new ArithmeticException("divide by zero");}

        double m = squaredModulus();
        if(m==0){
            throw new ArithmeticException("divide by zero");
        }
        return new Complex(real / m, -imaginary / m);
    }

    // @return the complex number <code>this / divisor</code>

    Complex divide(Complex divisor) {
        if(divisor.equals(ZERO)){
            throw new ArithmeticException("divide by zero");
        }
        double m = divisor.squaredModulus();
        if(m==0){
            throw new ArithmeticException("divide by zero");
        }
        return new Complex(
                (this.real * divisor.real + this.imaginary * divisor.imaginary) / m,
                (this.imaginary * divisor.real - this.real * divisor.imaginary) / m
        );
    }

    //@return the complex number <code>this ** p</code>

    Complex pow(int p) {
        if (p == 0)
            return ONE;
        Complex result = (this.multiply(this)).pow(p / 2);
        if (p % 2 == 1)
            result = result.multiply(this);
        return result;
    }

    /**
     * Scalar multiplication of a complex number
     * @param lambda a scalar number
     * @return the complex number <code>lambda * this</code>
     */
    public Complex scale(double lambda) {
        return new Complex(lambda * real, lambda * imaginary);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Complex complex = (Complex) o;
        return Helpers.doubleCompare(complex.real, real)==0 &&
                Helpers.doubleCompare(complex.imaginary, imaginary)==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }


    @Override
    public String toString() {
        return "Complex{" +
                "real=" + real+
                ", imaginary=" + imaginary +
                '}';
    }
}
