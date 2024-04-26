package Datastructures.Trees;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of a general tree.
 * It has 1 or more children for each node.
 * **/
public class GeneralTree<T extends Comparable<T>> {

    private T data;

    private List<GeneralTree> children;
    private GeneralTree<T> parent;
    public GeneralTree(T data){
        this.data = data;
        children = new ArrayList<>();
        this.parent = null;
    }

    /**
     * returns the value of the tree node
     * @return the value of the tree
     * **/
    private T getData(){
        return this.data;
    }

    /**
     * returns the root of the tree.
     * @return the root node of the general tree.
     * **/
    public GeneralTree getRoot(){
        return this;
    }

    /**
     * returns the parent of a tree node.
     * @return the parent of a tree node.
     * **/
    public GeneralTree getParent(){
        return parent;

    }

    /**
     * returns the list of children of a tree node
     * @return a list of children of a tree node
     * */
    public List<GeneralTree> getChildren(){
        return children;
    }

    /**
     * checks if a tree node is a leaf or an internal node.
     * @return true if a tree node is an internal node
     * @return false if a tree node is not an internal node.
     * **/
    public static boolean isInternal(GeneralTree node){

        if( node.getChildren().isEmpty()){
            return false;
        }

        return true;
    }

    /**
     * checks if a tree node is a leaf is an external node.
     * @return true if a tree node is an external node
     * @return false if a tree node is not an external node.
     * **/
    public static boolean isExternal(GeneralTree node){
        if( GeneralTree.isInternal(node) ){
            return false;
        }
        return true;
    }

    /**
     * checks if a node ins the root node of the tree.
     * @return true if the tree node is the root node is the root node.
     * @return false if the tree node is the root node is not the root node.
     * **/
    public static boolean isRoot(GeneralTree node){
        if (node.getParent() == null){
            return true;
        }
        return false;

    }

    /**
     * gets the number of nodes in the tree.
     * @return the number of nodes in the tree.
     * **/
    public int size(){
        int size = 0 ;

        if(GeneralTree.isExternal(this)){
            size += 1;
        }

        for (GeneralTree node : this.getChildren()){
            size += node.size();
        }

        return size;
    }

    /**
     * adds a child to the list of child node.
     * @param node the node being added.
     * */

    public void addChild(GeneralTree node){
        this.children.add(node);
    }

    /***
     * stets the data of a tree node
     * @param data the value being set to.
     * */

    public void setData(T data) {
        this.data = data;
    }

    /**
     * sets the parent node of a tree
     * @param parent the new parent node
     *
     * **/
    public void setParent(GeneralTree<T> parent) {
        this.parent = parent;
    }

    /**
     * gets the height of the tree
     * @return the height of the tree.
     * **/

    public int getHeight(){
        int height = 1;
        int childHeights = 0;

        if (isExternal(this)){
            return 0;
        }

        for (GeneralTree child : children){
            if ( isInternal(child)){
                childHeights = Math.max(child.getHeight(),childHeights);
            }
        }

        return height + childHeights;

    }

    /**
     * Reruns a string representing the structure of the tree.
     * @return  A string representation of the tree structure
     * **/
    public String toString(){
        StringBuilder out = new StringBuilder();

        out.append(this.data);

        for (GeneralTree node : this.getChildren()){
            out.append(" ");
            out.append(node.toString());

        }

        return out.toString();
    }

}
