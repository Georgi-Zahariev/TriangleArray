
//Динамичен тригълен масив от общ тип
// "Псевдоквадратен" масив, симетричен относно
//  диагонала. Помним елементите по и над диагонала.
/*  Напр. при n=5:
 * 10 11 12 13 14
 *  6  7  8  9 13
 *  3  4  5  8 12 -> 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 *  1  2  4  7 11
 *  0  1  3  6 10
 */
import java.util.ArrayList;

class TriArray<T> {
	private ArrayList<T> arr; // Указател към масива
	private int cnt; // размер на катета

	public TriArray(int n) { // конструктор по размер на катета
		cnt = n; // съхраняване на размера
		n = (n * (n + 1)) >>> 1; // брой елементи
		arr = new ArrayList<T>(n); // динамичен масив с определен брой елементи
		for (int i = 0; i < n; i++)
			arr.add(null); // инициализация на указателите към елементите
	}

	// Частен метод за намиране на линейния адрес по координати
	private int getIndex(int x, int y) throws IndexOutOfBoundsException {
		// Проверка за принадлежност към масива
		if (x >= cnt || y >= cnt)
			throw new IndexOutOfBoundsException();
		// Размяна на координатите, ако x>y
		if (x > y) {
			int t = x;
			x = y;
			y = t;
		}
		return ((y * (y + 1)) >>> 1) + x; // адрес в едномерния масив
	}

	// Гетъри
	public int getCnt() {
		return cnt;
	}

	public T get(int x, int y) {
		int n = getIndex(x, y);
		return arr.get(n);
	}

	// Сетър
	public void set(int x, int y, T val) {
		int n = getIndex(x, y);
		arr.set(n, val);
	}
}

public class Main {
	public static void main(String[] args) {
		TriArray<Integer> t = new TriArray<Integer>(5);
		for (int y = 0; y < t.getCnt(); y++)
			for (int x = 0; x <= y; x++)
				t.set(x, y, ((y * (y + 1)) >>> 1) + x);
		for (int y = 0; y < t.getCnt(); y++) {
			for (int x = 0; x < t.getCnt(); x++)
				System.out.format("%3d", t.get(x, y));
			System.out.println();
		}
		try {
			System.out.println(t.get(4, 0));
		} catch (Exception e) {
			System.out.println("Out of boundaries");
		}
	}
}
