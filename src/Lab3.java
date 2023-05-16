import java.util.ArrayList;	//імпорт масиву динамічного розміру, через невідому кількість речень
public class Lab3 {
	public static void main(String[] args) {
		StringBuilder origText = new StringBuilder("В залежності від C17 визначити дію з рядком.\n Мій варіант: "
													+"З кожного речення заданого тексту видалити підрядок найбільшої довжини,"
													+" що починається та закінчується заданими літерами.");
		StringBuilder taskLetters = new StringBuilder("ді");
		ArrayList<StringBuilder> sentences = new ArrayList<StringBuilder>();
		boolean redacted = false;	//У випадку якщо текст не відповідає умовам, вивести повідомлення про відсутність змін
		char ch;	//Ініціалізація змінної, у яку будуть записуватись значення символів тексту
		int lastEnd = 0;	//Місце останнього кінця речення
		sentences.add(new StringBuilder(origText.substring(0)));
		for (int i = 0; i < origText.length(); i++) {
			ch = origText.charAt(i);
			if (ch == '.' || ch == '!' || ch == '?'){
				sentences.set(sentences.size()-1, sentences.get(sentences.size()-1).delete(i+1-lastEnd, origText.length()));	//Скорочення минулої строки до цього символу
				sentences.add(new StringBuilder(origText.substring(i+1)));	//Додавання залишку тексту у массив
				lastEnd = i+1;
			}
		}
		int indexStart = -1;	//Місце першого появлення першої заданої літери
		int indexEnd = -1;	//Місце останнього появлення другої заданої літери
		for (int i = 0; i < sentences.size(); i++){
			indexStart = sentences.get(i).indexOf(taskLetters.substring(0,1));	//Пошук першого появлення першої заданої літери
			indexEnd = sentences.get(i).lastIndexOf(taskLetters.substring(1,2));	//Пошук останнього появлення другої заданої літери
			if (indexStart < indexEnd && indexStart != -1){
				sentences.set(i, sentences.get(i).delete(indexStart, indexEnd+1));	//Видалення відповідної підстроки
				redacted = true;
			}
		}
		if (redacted) {
			StringBuilder redactedText = new StringBuilder();
			for (int i = 0; i < sentences.size(); i++){
				redactedText.append(sentences.get(i));	//Переведення масиву у єдину строку
			}
			System.out.println("\nПочатковий текст:\n\""+origText+"\"");
			System.out.println("\nШукані літери: \'"+taskLetters.substring(0,1)+"\', \'"+taskLetters.substring(1,2)+"\'");
			System.out.println("\nВідредагований текст:\n\""+redactedText+"\"");
		} else {
			System.out.println("\nТекст не відповідає умовам; текст залишен без змін:\n\""+origText+"\"");
		}
	}
}

