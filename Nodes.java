
public class Nodes {
int numInstruction;
String instruction, jump;
	public Nodes(int numInstruction, String instruction, String jump  ) {
		this.numInstruction=numInstruction;
		this.instruction=instruction;
		this.jump=jump;
	}
	public int getNumInstruction() {
		return numInstruction;
	}
	public void setNumInstruction(int numInstruction) {
		this.numInstruction = numInstruction;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getJump() {
		return jump;
	}
	public void setJump(String jump) {
		this.jump = jump;
	}
	
}
