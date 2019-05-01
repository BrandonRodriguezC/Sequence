import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	static int count = 1;
	static ArrayList<Nodes> list = new ArrayList<Nodes>();
	static Stack<Close> stack = new Stack<Close>();
	static int j = 0;

	/**
	 * @param line
	 */

	public static void process(String vec[]) {
		for (int i = 0; i < vec.length; i++) {
			String line= vec[i];
			line = line.trim();
			String[] vector = line.split(" ");
			//printStack();

			if (vector[0].equals("If") || vector[0].equals("if")) {

				Nodes node = new Nodes(count, withoutBrackets(vector[1]), "");
				list.add(node);
				Close close = new Close(list.size() - 1, vector[0]);
				stack.push(close);
				count++;
			} else if (vector[0].equals("Else") || vector[0].equals("else")) {

				Nodes node = new Nodes(count, "jump", "");
				list.add(node);
				Close close = new Close(list.size() - 1, vector[0]);
				stack.push(close);
				count++;
			} else if (vector[0].equals("While") || vector[0].equals("while")) {

				Nodes node = new Nodes(count, withoutBrackets(vector[1]), "");
				list.add(node);
				Close close = new Close(list.size() - 1, vector[0]);
				stack.push(close);
				count++;
			} else if (vector[0].equals("Iterate") || vector[0].equals("iterate")) {

				Nodes node = new Nodes(count, vector[1], "");
				list.add(node);
				Close close = new Close(list.size() - 1, vector[0]);
				stack.push(close);
				count++;
			} else if (vector[0].substring(vector[0].length() - 1, vector[0].length()).equals(";")) {

				Nodes node = new Nodes(count, vector[0], "");
				list.add(node);
				count++;
			} else if (vector[0].equals("}")) {

				int number = stack.get(stack.size() - 1).getNumLine();
				String close = stack.get(stack.size() - 1).getCloseA();

				if (close.equals("If") || close.equals("if")) {
					
					if(vec[i+1].trim().equals("else")) {
						
						list.get(number).setJump(" "+(count+1));
					}else {
						list.get(number).setJump(" "+count );
					}
					
					stack.pop();

				} else if (close.equals("Else") || close.equals("else")) {

					list.get(number).setJump(" "+count);
					stack.pop();

				} else if (close.equals("While") || close.equals("while")) {

					Nodes node = new Nodes(count, "jump", " "+(number + 1) );
					list.add(node);
					list.get(number).setJump(" "+(count + 1) );
					stack.pop();
					count++;
				} else if (close.equals("Iterate") || close.equals("iterate")) {

					list.get(number).setJump(" "+(count + 1) );
					stack.pop();
					Nodes node = new Nodes(count, "jump", " "+(number + 1) );
					list.add(node);
					count++;
				}

			}

		}
	}

	public static void printStack() {
		System.out.println("-------------------------STACK--------------------------");
		for (int i = 0; i < stack.size(); i++) {
			System.out.println(stack.get(i).getNumLine() + " " + stack.get(i).getCloseA());
		}
		System.out.println("------------------------- END STACK--------------------------");
	}

	public static void printList(ArrayList<Nodes> list) {
		Nodes node;
		//System.out.println("------------------------- List--------------------------");
		for (int i = 0; i < list.size(); i++) {
			node = list.get(i);
			System.out.println(node.getNumInstruction() + " " + node.getInstruction()  + node.getJump());
		}
	}

	public static String withoutBrackets(String line) {
		return line.substring(1, line.length() - 1);
	}

	public static void main(String[] args) throws IOException {
		File archivo = new File("test.in");
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			int number = Integer.parseInt(line);
			String vec[] = new String[number];
			for (int i = 0; i < number; i++) {
				line = br.readLine();
				vec[i] = line;
			}
			process(vec);
			Nodes end= new Nodes(count, "end", "");
			list.add(end);
			printList(list);
			list.clear();
			stack.clear();
			count = 1;
		}

	}

}
