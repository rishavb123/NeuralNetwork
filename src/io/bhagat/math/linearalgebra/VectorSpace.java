package io.bhagat.math.linearalgebra;


//TODO finish and document this
/**
 * @author Bhagat
 *
 */
public class VectorSpace<E extends VectorSpace.VectorSpacable<E>> {

	private E[] basis;
	
	public VectorSpace(@SuppressWarnings("unchecked") E... basis) {
		this.basis = basis;
	}
	
	public boolean includes(E obj) {
		return obj.asLinearCombination(basis) != null;
	}
	
	public Vector coordinateVector(E obj) {
		return obj.asLinearCombination(basis);
	}
	
	public E fromCoordinateVector(Vector v) {
		if(v.getSize() != basis.length)
			return null;
		E total = basis[0].multiply(v.get(0));
		for(int i = 1; i < basis.length; i++)
			total = total.add(basis[i].multiply(v.get(i)));
		return total;
	}
	
	public static interface VectorSpacable<S> {
		
		public S add(S d);
		
		public S multiply(double v);
		
		public Vector asLinearCombination(@SuppressWarnings("unchecked") S... set);
		
	}
	
}
