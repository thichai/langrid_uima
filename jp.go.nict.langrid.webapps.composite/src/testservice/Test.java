package testservice;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public String[] decodeTuple(String value)
			throws ParseException
		{
			List<String> tuples = new ArrayList<String>();
			int offset = -1;
			int nest = 0;
			int valueNest = -1;
			StringBuilder b = new StringBuilder();
			for(char c : value.toCharArray()){
				offset++;
				switch(c){
					case '(':
						nest++;
						if(b.toString().trim().length() != 0){
							throw new ParseException(
									"Tuple contains invalid expression: " + value
									, offset);
						}
						b = new StringBuilder();
						break;
					case ')':
						if(nest == 0){
							throw new ParseException(
									"Tuple contains invalid expression: " + value
									, offset);
						}
						String v = b.toString().trim();
						if(v.length() > 0){
							if(valueNest == -1){
								valueNest = nest;
							} else if(valueNest != nest){
								throw new ParseException(
										"Tuple contains invalid expression: " + value
										, offset);
							}
							tuples.add(v);
							b = new StringBuilder();
						}
						nest--;
						break;
					default:
						b.append(c);
					break;
				}
			}
			if(nest != 0){
				throw new ParseException(
						"Too few close bracket: " + value
						, offset);
			}
			String v = b.toString().trim();
			if(v.length() > 0){
				if(valueNest == -1){ 
					tuples.add(v);
				} else{
					throw new ParseException(
							"Tuple contains invalid expression: " + value
							, offset);
				}
			}
			return tuples.toArray(new String[]{});
		}

}
