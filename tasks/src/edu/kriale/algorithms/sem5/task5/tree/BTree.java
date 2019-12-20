package edu.kriale.algorithms.sem5.task5.tree;

import java.util.Arrays;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BTree {
    private static final String EMPTY_TREE_STRING = "[пустое]";

    public static BTreeNode root = null;


    public static void main(String[] args) {
        //Initialize the root of the tree to null

        try {
            //Setup the input and output files
            FileReader iFile = new FileReader("tree_operations");
            BufferedReader in = new BufferedReader(iFile);
            FileWriter oFile = new FileWriter("BTree_results");
            BufferedWriter out = new BufferedWriter(oFile);

            //For each line in the file
            while(in.ready()) {
                //Split the line so that we can extract it's operation and operand(s)
                String[] currentInput = in.readLine().split("\\s");
                String command = currentInput[0];

                //Perform commands based off of operation specified
                if(command.equals("INS")) {
                    insert(search(root, Integer.parseInt(currentInput[1])), Integer.parseInt(currentInput[1]));
                }
                if(command.equals("DEL")) {
                    //Not implemented
                }
                if(command.equals("INO")) {
                    out.write(inOrderTraverse(root));
                    out.write("\n");
                }
                if(command.equals("PRE")) {
                    //Not implemented
                    out.write("\n");
                }
                if(command.equals("PST")) {
                    //Not implemented
                    out.write("\n");
                }
                if(command.equals("RNG")) {
                    //Not implemented
                    out.write("\n");
                }
                if(command.equals("FNG")) {
                    //Not implemented
                    out.write("\n");
                }
            }

            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            //Couldn't find the input file
            e.printStackTrace();
        } catch (IOException e) {
            //Couldn't write to the output file
            e.printStackTrace();
        }
    }


    private static class BTreeNode {
        public Integer nodeType;
        public Integer s, l;
        public BTreeNode small, middle, large, parent;

        public BTreeNode(Integer nodeType, Integer s, Integer l, BTreeNode parent) {
            this.nodeType = nodeType;
            this.s = s;
            this.l = l;
            this.small = null;
            this.middle = null;
            this.large = null;
            this.parent = parent;
        }
    }

    public void print() {
        if (root != null) {
            System.out.println(inOrderTraverse(root));
        } else {
            System.out.println(EMPTY_TREE_STRING);
        }
    }

    private static String inOrderTraverse(BTreeNode currentNode) {
        String result = "";
        if(currentNode != null) {
            result += inOrderTraverse(currentNode.small);
            if(currentNode.s != null) {
                result += currentNode.s + " ";
            }
            result += inOrderTraverse(currentNode.middle);
            if(currentNode.l != null) {
                result += currentNode.l + " ";
            }
            result += inOrderTraverse(currentNode.large);
        }
        return result;
    }


//    public Optional<Integer> search(int key) {
//        return Optional.ofNullable(search(root, key));
//    }

    private static BTreeNode search(BTreeNode currentNode, Integer item) {
        if(currentNode != null) {
            if(currentNode.nodeType == 3) {
                if(currentNode.s >= item && currentNode.l <= item) {
                    return currentNode;
                } else {
                    if(item < currentNode.s) {
                        return currentNode.small == null ? currentNode : search(currentNode.small, item);
                    } else if(item > currentNode.l) {
                        return currentNode.large == null ? currentNode : search(currentNode.large, item);
                    } else {
                        return currentNode.middle == null ? currentNode : search(currentNode.middle, item);
                    }
                }
            } else {
                if (currentNode.s.equals(item)) {
                    return currentNode;
                } else {
                    if (item < currentNode.s) {
                        return currentNode.small == null ? currentNode : search(currentNode.small, item);
                    } else {
                        return currentNode.large == null ? currentNode : search(currentNode.large, item);
                    }
                }
            }
        }
        return null;
    }

    public static void insert(BTreeNode currentNode, Integer item) {
        if (currentNode == null) {
            root = new BTreeNode(2, item, null, null);
        } else if (currentNode.nodeType == 2) {
            currentNode.nodeType = 3;
            currentNode.l = Math.max(item, currentNode.s);
            currentNode.s = Math.min(item, currentNode.s);
        } else {
            insertHelper(currentNode, item, null);
        }
    }

    public static void insertHelper(BTreeNode currentNode, Integer item, BTreeNode remainder) {
        BTreeNode parent, small, large;
        SortedMap<Integer, BTreeNode> nodeList = new TreeMap<Integer, BTreeNode>();

        if (currentNode.parent == null) {
            parent = new BTreeNode(null, null, null, null);
        } else {
            parent = currentNode.parent;
        }

        // New children
        small = new BTreeNode(2, Math.min(currentNode.s, Math.min(currentNode.l, item)), null, parent);
        large = new BTreeNode(2, Math.max(currentNode.s, Math.max(currentNode.l, item)), null, parent);

        // Is not a leaf
        if (currentNode.small != null && currentNode.middle != null && currentNode.large != null) {
            nodeList.put(currentNode.small.s, currentNode.small);
            nodeList.put(currentNode.large.s, currentNode.large);
            nodeList.put(currentNode.middle.s, currentNode.middle);
            nodeList.put(remainder.s, remainder);

            small.small = popNodeList(nodeList);
            small.large = popNodeList(nodeList);
            large.small = popNodeList(nodeList);
            large.large = popNodeList(nodeList);

            small.small.parent = small;
            small.large.parent = small;
            large.small.parent = large;
            large.large.parent = large;

        }

        int median = median(currentNode.s, currentNode.l, item);
        if (parent.nodeType == null) {
            parent.nodeType = 2;
            parent.s = median;
            parent.small = small;
            parent.large = large;
            root = parent;
            return;
        }
        if (parent.nodeType == 2) {
            parent.nodeType = 3;
            parent.s = Math.min(median, parent.s);
            parent.l = Math.max(median, parent.s);
            if (parent.small == currentNode) {
                if (small.s < parent.s) {
                    parent.middle = large;
                    parent.small = small;
                } else {
                    parent.middle = small;
                    parent.small = large;
                }
            } else {
                if(small.s < parent.s) {
                    parent.middle = large;
                    parent.large = small;
                } else {
                    parent.middle = small;
                    parent.large = large;
                }
            }
            return;
        }
        if(parent.nodeType == 3) {
            if(parent.small == currentNode) {
                parent.small = large;
                insertHelper(parent, median, small);
            } else {
                parent.large = small;
                insertHelper(parent, median, large);
            }
        }
    }

    public static Integer median(int i, int j, int k) {
        int[] valueList = {i, j, k};
        Arrays.sort(valueList);
        return valueList[1];
    }

    public static BTreeNode popNodeList(SortedMap<Integer, BTreeNode> nodeList) {
        if(!nodeList.isEmpty()) {
            BTreeNode temp = nodeList.get(nodeList.firstKey());
            nodeList.remove(nodeList.firstKey());
            return temp;
        } else {
            return null;
        }
    }
}
