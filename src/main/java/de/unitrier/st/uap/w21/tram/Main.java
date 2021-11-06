
package de.unitrier.st.uap.w21.tram;
final class Main
{
	private Main(){}
	
	public static void main(String[] argv)
	{
		// TODO: Create an instance of the abstract machine with respective parameters
		System.out.println(new AbstractMachine(Instruction.program1).interpret());
	}
}