
public class Level2 extends Level1{
	
	private String[] Q = {"Which continent is known as the 'Land Down Under'?",
	        "What is the largest desert in the world?",
	        "Which country is famous for the Amazon Rainforest?",
	        "What is the capital city of France?",
	        "Which ocean is the largest and deepest?",
	        "Which country is known for the Great Wall?",
	        "Which is the highest mountain in Africa?",
	        "Which is the longest river in South America?",
	        "Which country is located on the Scandinavian Peninsula?",
	        "What is the largest country by land area?"};
	
	private String[][] OL = {{"Asia", "Africa", "Australia", "Europe"},
	        {"Sahara Desert", "Gobi Desert", "Atacama Desert", "Antarctica"},
	        {"Brazil", "Mexico", "Canada", "Russia"},
	        {"Paris", "London", "Rome", "Tokyo"},
	        {"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"},
	        {"China", "India", "Japan", "Australia"},
	        {"Mount Everest", "Kilimanjaro", "Matterhorn", "Andes"},
	        {"Amazon River", "Nile River", "Mississippi River", "Yangtze River"},
	        {"Sweden", "Denmark", "Finland", "Norway"},
	        {"Russia", "Canada", "China", "United States"}};
	
    private	String[] CA = {"Australia", "Sahara Desert", "Brazil", "Paris", "Pacific Ocean",
	        "China", "Kilimanjaro", "Amazon River", "Norway", "Russia"};
	
	public Level2() {
		super();
		super.set_questions(Q, OL, CA);
    }
}
