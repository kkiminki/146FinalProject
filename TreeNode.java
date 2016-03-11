//Test change
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TreeNode {
   static Random r = new Random();
   static int nActions = 5;
   static double epsilon = 1e-6;
   
   TreeNode[] children;
   double nVisits, toValue;

   public void selectAction(){
      List<TreeNode> visited = new LinkedList<TreeNode>();
      TreeNode cur = this;
      visited.add(this);
      while (!cur.isLeaf()){
         cur = cur.select();
         visited.add(cur);
      }
      cur.expand();
      TreeNode newNode = rollOut(newNode);
      double value = rollout(newNode);
      for (TreeNode node : visited){
         node.updateStats(value);
      }
   }

   public void expand(){
      children = new TreeNode[nActions];
      for(int i = 0; i < nActions; i++){
         children[i] = new TreeNode();
      }
   }

   private TreeNode select(){
      TreeNode selected = null;
      double bestValue = Double.MIN_VALUE;
      for(TreeNode c: children){
         double uctValue = c.totValue / (c.nVisits + epsilon) + Math.sqrt(Math.log(nVisits + 1) / (c.nVisits + epsilon)) + r.nextDouble() * epsilon;
         if (uctValue > bestValue){
            selected = c;
            bestValue = uctValue;
         }
      }
      return selected;
   }

   public boolean isLeaf(){
      return children == null;
   }

   public double rollOut(TreeNode tn){
      return r.nextInt(2);
   }

   public void updateStats(double value){
      nVisits++;
      totValue += value;
   }

   public int arity(){
      return children == null ? 0: children.length;
   }
}  
