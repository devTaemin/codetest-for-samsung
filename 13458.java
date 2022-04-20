class Main {
	public static long count;
	public static int N, B, C;
	public static int[] rooms;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		rooms = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			rooms[i] = sc.nextInt();
		}
		
		B = sc.nextInt();
		C = sc.nextInt();
		
		Inspector();
		sc.close();
	}
	
	public static void Inspector() {
		
		for (int i = 1; i <= N; i++) {
			// rooms[i] >= 1
			if (rooms[i] < B) { rooms[i] = 0; }
			else { rooms[i] -= B; }
			count++;
		}
		
		for (int i = 1; i <= N; i++) {
			if (rooms[i] != 0) {
				int div = rooms[i] / C;
				int rest = rooms[i] % C;
				
				if (rest != 0) div += 1;
				
				count += div;
			}
		}

		System.out.println(count);
	}
}