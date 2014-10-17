package com.interview.basics.search.array;

/**
 * http://www.cs.princeton.edu/~rs/talks/LLRB/RedBlack.pdf
 */
public class RBTSearcher<T extends Comparable<T>> extends BSTSearcher<T>{
	class RBTNode<T> extends BSTNode<T>{
		public static final boolean BLACK = false;
		public static final boolean RED = true;
		public boolean color = BLACK;

		public RBTNode(int index, T value) {
			super(index, value);
			this.color = RED;
		}
		
		public boolean isRed(BSTNode node){
			if(node == null) return false;
			else return ((RBTNode)node).color == RED;
		}
		
		public RBTNode rotateRight(){
			RBTNode tmp = (RBTNode) this.left;
			this.left = tmp.right;
			tmp.right = this;
			tmp.color = this.color;
			this.color = RED;
			return tmp;
		}
		
		public RBTNode rotateLeft(){
			RBTNode tmp = (RBTNode) this.right;
			this.right = tmp.left;
			tmp.left = this;
			tmp.color = this.color;
			this.color = RED;
			return tmp;
		}
		
		public void flipColors(){
			this.color = RED;
			((RBTNode)this.left).color = BLACK;
			((RBTNode)this.right).color = BLACK;
		}
	}

	public RBTSearcher(T[] input) {
		super(input);
	}
	
	@Override
	protected BSTNode<T> addNode(BSTNode<T> node, int index) {
		if(node == null){
			return new RBTNode<T>(index, input[index]);
		} else {
			RBTNode bnode = (RBTNode) node;
			if(node.value.compareTo(input[index]) < 0){
				node.right = addNode(node.right, index);
			} else {
				node.left = addNode(node.left, index);
			}
			if(bnode.isRed(bnode.right) && !bnode.isRed(bnode.left))	node = bnode.rotateLeft();
			if(bnode.isRed(bnode.left) && bnode.isRed(bnode.left.left)) node = bnode.rotateRight();
			if(bnode.isRed(bnode.left) && bnode.isRed(bnode.right))		bnode.flipColors();
			
			return node;
		}
	}
}
