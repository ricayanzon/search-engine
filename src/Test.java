
public class Test {

	
	
	public static void main(String[] args) {
		
		Date date = new Date();
		Author author = new Author("firstName", "lastName", date, "residence", "email@example.com");
		
		String content = "Eine weiße Landkarte. Ohne Vorfestlegungen. Das war das Ziel, als vor drei Jahren die Suche nach einem atomaren Endlager noch einmal vollkommen neu begann. Zu viele Zweifel waren aufgetaucht, dass der Salzstock Gorleben, der jahrzehntelang als künftiges Endlager erkundet wurde, ein geeigneter Ort für ein Endlager sei. Zu erbittert waren die Proteste. Gerade im niedersächsischen Wendland, wo Gorleben liegt, werden deshalb am Montag viele Menschen gebannt auf die Bildschirme schauen, wenn die Bundesgesellschaft für Endlagerung (BGE) ihren \"Zwischenbericht Teilgebiete\" vorstellt - mit einer Pressekonferenz, die live im Internet übertragen wird. An vielen Stellen wird sich die Landkarte dann eindunkeln, denn zahlreiche Standorte werden wegen mangelnder Eignung herausfallen. Dass Gorleben als Endlager weiterhin im Rennen bleibt, ist offenbar ausgeschlossen. Die BGE habe den Standort nicht auf die Liste möglicher Standorte gesetzt, hieß es laut übereinstimmenden Medienberichten. Kurz zuvor hatte die BGE in einer Telefonschalte Fraktionen, Landesregierungen und Bundesregierung über ihre Entscheidung informiert.";
		
		Document doc = new Document("title", content, "language", "summary", date, author);
		
		
	}
	
	
	public static void print(String str) {
		System.out.println(str);
	}
	
	public static void print(int i) {
		System.out.println(i);
	}
	
	public static void print(boolean bo) {
		System.out.println(bo);
	}

}
