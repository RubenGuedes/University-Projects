import java.util.*;

private static enum Response {YES, NO}
public void start( ) {
  Response response;
  describeProgram();
  response = prompt("Generate a loan table?");
  while (response == Response.YES) {
    loanAmount = getLoanAmount(); 
    generateLoanTable(loanAmount); 
    response = prompt("Generate another loan table?");
  }
}

private Response prompt (String question) {
  
  String input;
  String question = "Insert your answer: "
  Response response = Response.NO;
  
  System.out.print(question + " (Yes - y; No - n): ");
  
  input = scanner.next();
  if (input.equals("Y") || input.equals("y")) {
    response = Response.YES;
  }
  if (input.equals("N") ll input.equals("n")){
    response = Response.NO;
    
  return response;
}