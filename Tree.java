import java.util.ArrayList;
import java.util.Collections;

public class Tree
{
	public Node root;
	
	public Tree()
	{
		root = null;
	}
	
	/**
	 * Tree will need an insert(int x) method, which will insert the value x into
	 * your tree. For this tree, duplicate insertions should be discarded. That is,
	 * if I insert a value into the tree which is already in the tree, do not change
	 * your tree. Your method should return true if the element is added (which
	 * should happen if it isn't already in the tree), and false if it is not added
	 * (if the number was already in the tree).
	 * 
	 * @param x
	 * @return
	 */
	public boolean insert(int x)
	{
		boolean check = true;

		// Base case: tree is empty (starting with nothing)
		// Create a root node with the entered value
		if (root == null)
		{
			root = new Node(x);
		}
		
		// if a node's value is same as entered value, return false
//		else if (root.search(x))
//		{
//			check = false;
//		}
		else
		{
			// insert x into root
			root.insertNewValue(root, x);
		}
		
		return check;
	}
	
	/**
	 * Return the int size of the subtree
	 * rooted at the node that contains integer x. If x is not in the tree, it
	 * should return 0
	 * 
	 * For a node containing the entered number, retrieve the number of keys in that
     * node and its descendants.
	 * 
	 * @param x
	 * @return
	 */
	public int size(int x)
	{
		if (root.keys.contains(x))
		{
			root.search(x);
			return root.keys.size();
		}
		return 0;

	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Node
	{
		ArrayList<Integer> keys = new ArrayList<>(3);  // left key, right key, temp
		ArrayList<Node> child = new ArrayList<>(4);	// left child, middle child, right child, temp
		
		public Node(int newValue)
		{
			keys.add(newValue);		// Every time the constructor is called, Add a new value into the arraylist
		}
		
		/**
		 * A method to add a value to an arraylist and then sort the arraylist in ascending order
		 * @param newValue
		 */
		public void addValueToKeys(int newValue)
		{
			keys.add(newValue);
			Collections.sort(keys);
		}
		/**
		 * In all cases, check if 2-node or 3-node
		 * @param currentNode
		 * @param newValue
		 * @return
		 */
		public Node insertNewValue(Node currentNode, int newValue)
		{
			
			// Case 1: new value is smaller than the left key of current node
			if (newValue < currentNode.keys.get(0))
			{
				// check if the left child has only one key (2-node)
				// if so, then insert normally
				if (currentNode.child.get(0).keys.get(1) == 0)	// size?
				{
					Node newChild = insertNewValue(currentNode.child.get(0), newValue);
					child.set(0, newChild);
					return child.get(0);
				}
				
				// If it's a 3 node, shift the keys and elements to make it a 2 node
				else
				{
					Node newChild = insertNewValue(currentNode.child.get(0), newValue);
					child.set(0, newChild);
					if (keys.get(1) == 0 && child.get(0).keys.get(1) == 0)
					{
						Collections.sort(keys);						// sort keys in ascending order
						keys.set(0, child.get(0).keys.get(0));		// set left data element as left child's left data element
						child.set(1, child.get(0).child.get(2));	// set middle child as left child's right child
						child.set(0, child.get(0).child.get(0));	// set left child as left child's left child
						//childrenNodes.get(1).keys.set(1, childrenNodes.get(0).keys.get(2));

					}
				}
				return currentNode;
			}
			
			// Case 2: new value is greater than the right data element in the current node
			else if ( newValue > currentNode.keys.get(0))
			{
				// check if 2 node
				if (currentNode.child.get(2).keys.get(1) == 0)
				//if (currentNode.child.get(keys) == 0)
				{
					Node newChild = insertNewValue(currentNode.child.get(2), newValue);
					child.set(2, newChild);
					return child.get(2);
				}
				
				// if it's a 3-node, shift accordingly
				else
				{
					Node newChild = insertNewValue(currentNode.child.get(2), newValue);
					child.set(2, newChild);
					if (keys.get(1) == 0 && child.get(2).keys.get(1) == 0)
					{
						keys.set(1, child.get(2).keys.get(0));	// set the right data element as the right child's left key
						child.set(1, child.get(2).child.get(0));	// set the middle child as the right child's left child	(shift left)
						child.set(2, child.get(2).child.get(2));	// set the right child as the right child's right child (move up)

					}
				}
				return currentNode;
			}
			// Case 3: new value is in between left and right key
			else if (newValue > currentNode.keys.get(0) && newValue < currentNode.keys.get(1))
			{
				// check if it's a 2 node
				if (currentNode.child.get(1).keys.get(1) == 0)
				{
					Node newChild = insertNewValue(currentNode.child.get(1), newValue);
					child.set(1, newChild);
					return child.get(1);
				}
				
				// if it's a 3 node, shift accordingly 
				else
				{
					Node newChild = insertNewValue(currentNode.child.get(1), newValue);
					child.set(1, newChild);
					//split?
				}
			}
			
			
			
			// Case 4: none of the above (could be a leaf node)
			return currentNode;
			
			
		
		}
		// need search method.... use while loop and compareTo?

		public Node search (int x)
		{
			int i;
			for (i = 0; i < keys.size(); i++)	// for every value in the keys arraylist
			{
				if (x == keys.get(i))			// if the desired value is found
				{
					return child.get(i);	// return the node containing the desired value
					
				}
			}
			return child.get(i).search(x);	//recursively search until the value is found.
			
		}
		
		
		
	}
	
	
}
