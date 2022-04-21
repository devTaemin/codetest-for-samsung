class Main {
	public static int N;
	public static int[] T;
	public static int[] P;
	public static int[] MAX;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		T = new int[N + 1];
		P = new int[N + 1];
		MAX = new int[N + 2];
		
		for (int i = 1; i <= N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		
		Run();
		System.out.println(MAX[1]);
	}
	
	public static void Run() {
		for (int day = N; day >= 1; day--) {
			if (N - day + 1 < T[day]) {
				MAX[day] = MAX[day + 1];
				continue;
			}
			MAX[day] = Math.max((P[day] + MAX[day + T[day]]), MAX[day + 1]);
		}
	}
}