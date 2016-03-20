import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String []args) {
		class Main2 {
			List <Integer> votes;
			List <List <Integer>> permutations;
			List <Integer> current_permutation;
			List <Integer> key_coalitions;
			List <List <Integer>> key_coalitions_size;
			int q = 35;
			int n;
			void generate_permutations (int j) {
				if (j == n) {
					//permutations.add(current_permutation);
					List<Integer> new_p = new ArrayList<Integer>();
					for (int k = 0; k < current_permutation.size(); ++k)
						new_p.add(current_permutation.get(k));
					permutations.add(new_p);
					return;
				}
				for (int i = 0; i < 2; ++i) {
					current_permutation.set(j, i);
					generate_permutations(j + 1);
				}
			}
			int sum (int i) {
				int s = 0;
				for (int j = 0; j < permutations.get(i).size(); ++j)
					s+=permutations.get(i).get(j);
				return s;
			}
			int coalition_size (int i) {
				int k = 0;
				for (int j = 0; j < permutations.get(i).size(); ++j)
					if (permutations.get(i).get(j) != 0)
						k++;
				return k;
			}
			public int fact(int num) {
			     return (num == 0) ? 1 : num * fact(num - 1);
			}
			void start () {
				votes = new ArrayList<Integer>(Arrays.asList(30, 20, 15, 10)); //////////////////
				current_permutation = new ArrayList<Integer>();
				key_coalitions = new ArrayList<Integer>();
				n = votes.size();
				key_coalitions_size = new ArrayList<List<Integer>>();
				for (int i = 0; i < n; ++i) {
					current_permutation.add(0);
					key_coalitions.add(0);
					key_coalitions_size.add(new ArrayList<Integer>());
				}
				permutations = new ArrayList<List<Integer>>();
				generate_permutations(0);
				for (int i = 0; i < permutations.size(); ++i) {
					for (int j = 0; j < permutations.get(i).size(); ++j)
						permutations.get(i).set(j, permutations.get(i).get(j)*votes.get(j));
				}
//				for (int i = 0; i < permutations.size(); ++i) {
//					for (int j = 0; j < permutations.get(i).size(); ++j)
//						System.out.print (permutations.get(i).get(j));
//					System.out.println();
				for (int i = 0; i < permutations.size(); ++i) {
					int s = sum(i);
					int k = coalition_size(i);
					if (s >= q) {
						for (int j = 0; j < permutations.get(i).size(); ++j) {
							if (s - permutations.get(i).get(j) < q) {
								key_coalitions.set(j, key_coalitions.get(j)+1);
								key_coalitions_size.get(j).add(k);
							}
						}
					}
				}
				int total_sum = 0;
				for (int i = 0; i < key_coalitions.size(); ++i)
					total_sum += key_coalitions.get(i);
				System.out.println("Bancaf index");
				for (int i = 0; i < key_coalitions.size(); ++i) {
					System.out.println(i + " = " + key_coalitions.get(i) + '/' + total_sum + '=' + (double) key_coalitions.get(i)/total_sum);
				}
				System.out.println("Shepli-Shubic:");
				for (int i = 0; i < key_coalitions_size.size(); ++i)
				{
					double SS = 0;
					for (int j = 0; j < key_coalitions_size.get(i).size(); ++j) {
						int s = key_coalitions_size.get(i).get(j);
						SS += (double)fact(n-s)*fact(s-1)/fact(n);
					}
					System.out.println(i + " = " + SS);
				}
			}
		}
		Main2 main2 = new Main2();
		main2.start();
	}
}
