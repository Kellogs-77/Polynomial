package poly;

import java.io.IOException;

import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node sumPoly = null;
		Node sumPolyptr = sumPoly;
		if(poly1 == null && poly2 == null) {
			return null;
		}
		Node ptr1 = poly1;
		Node ptr2 = poly2;

		
		while(ptr1!=null&&ptr2!=null) {
			if(ptr1 == null || ptr2 == null) {
				break;
			}
			if(ptr1.term.degree > ptr2.term.degree) {
				if(sumPoly == null) {
					sumPoly = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					sumPolyptr = sumPoly;
				} else {
					sumPolyptr.next = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					sumPolyptr = sumPolyptr.next;
				}
				ptr2 = ptr2.next;
			
			} else if(ptr1.term.degree < ptr2.term.degree) {
				if(sumPoly == null) {
					sumPoly = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					sumPolyptr = sumPoly;
				} else {
					sumPolyptr.next = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					sumPolyptr = sumPolyptr.next;
				}
				ptr1 = ptr1.next;	
			} else if(ptr1.term.degree == ptr2.term.degree) {
				float sumCoeff = ptr1.term.coeff + ptr2.term.coeff;
				if(sumCoeff == 0) {
					if(ptr1.term.degree==0) {
						if(sumPoly == null) {
							sumPoly = new Node(0, 0, null);
							sumPolyptr = sumPoly;
						} else {
							sumPolyptr.next = new Node(0, 0, null);
							sumPolyptr = sumPolyptr.next;
						}
					} 
				}
				else {
					if(sumPoly == null) {
						sumPoly = new Node(sumCoeff, ptr1.term.degree, null);
						sumPolyptr = sumPoly;
					} else {
						sumPolyptr.next = new Node(sumCoeff, ptr1.term.degree, null);
						sumPolyptr = sumPolyptr.next;
					}
				}
				ptr1 = ptr1.next;
				ptr2 = ptr2.next;

			}
		}
		if(ptr1!=null && ptr2 == null) {
			while(ptr1!=null) {
				if(sumPoly == null) {
					sumPoly = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					sumPolyptr = sumPoly;
				}
				else {
					sumPolyptr.next = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					sumPolyptr = sumPolyptr.next;
				}
				ptr1 = ptr1.next;
				if(ptr1 == null) {
					sumPolyptr.next = null;
				}
			}
		} else if(ptr1==null && ptr2 != null) {
			while(ptr2!=null) {
				if(sumPoly == null) {
					sumPoly = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					sumPolyptr = sumPoly;
				}
				else {
					sumPolyptr.next = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					sumPolyptr = sumPolyptr.next;
				}
				ptr2 = ptr2.next;
				if(ptr2 == null) {
					sumPolyptr.next = null;
				}
			}
		}
		
		return sumPoly;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		if(poly1 == null || poly2 == null) {
			return null;
		}
		Node productPoly = null;
		Node temp = null;
		Node tempPtr = temp;
		for(Node i = poly1; i != null; i = i.next) {
			
			for(Node j = poly2; j != null; j = j.next) {
				if(temp == null) {
					temp = new Node(i.term.coeff * j.term.coeff, i.term.degree+j.term.degree, null);
					tempPtr = temp;
				} else {
					tempPtr.next = new Node(i.term.coeff * j.term.coeff, i.term.degree+j.term.degree, null);
					tempPtr = tempPtr.next;
				}
				
			}
			if(productPoly == null) {
				productPoly = temp;
			} else {
				productPoly = add(temp, productPoly);
			}
			temp = null;
			tempPtr = temp;			
		}
		
		return productPoly;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node ptr = poly;
		float evaluatedPoly = 0;
		while(ptr!=null) {
			float degree = (float) (Math.pow(x, ptr.term.degree));
			float coeff = (float) degree * ptr.term.coeff;
			evaluatedPoly+= coeff;	
			ptr = ptr.next;
		}
		return evaluatedPoly;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
	
	
}
