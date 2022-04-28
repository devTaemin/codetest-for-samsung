class Main {
	public static int N, M, K, Result;
	public static int[][] Map;
	public static int[][] NextMap;
	public static ArrayList<ArrayList<ArrayList<Fireball>>> Current;
	public static ArrayList<ArrayList<ArrayList<Fireball>>> Next;
	
	// Move
	public static int[] Dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] Dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	
	
	public static void main(String[] args) {
		init();
		
		int count = 0;
		while(count < K) {
			first();
			second();
			count++;
		}
		
		Check();
		
		
	}
	
	public static void Check() {
		Result = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (Current.get(i).get(j).size() > 0) {
					for (int k = 0; k < Map[i][j]; k++) {
						Result += Current.get(i).get(j).get(k).m;
					}
				}
			}
		}
		
		System.out.println(Result);
	}
	
	
	public static void second() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (Map[i][j] > 1) {
					int size = Map[i][j];
					
					int totalM = 0;
					int totalS = 0;
					int[] arrD = new int[size];
					
					for (int k = 0; k < size; k++) {
						Fireball fb = Current.get(i).get(j).get(k);
						
						totalM += fb.m;
						totalS += fb.s;
						arrD[k] = fb.d;
					}
					
					for (int k = 0; k < size; k++) {
						Current.get(i).get(j).remove(0);
						Map[i][j] -= 1;
					}
					
					boolean isSame = true;
					int remain = arrD[0] % 2;
					for (int k = 1; k < size; k++) {
						if (arrD[k] % 2 != remain) {
							isSame = false;
							break;
						}
					}
					
					int[] direction;
					if (isSame) direction = new int[] { 0, 2, 4, 6 };
					else direction = new int[] { 1, 3, 5, 7 };
					
					for (int k = 0; k < 4; k++) {
						if (totalM / 5 > 0) {
							Current.get(i).get(j).add(new Fireball(i, j, totalM / 5, totalS / size, direction[k]));
							Map[i][j] += 1;
						}
					}
				}
			}
		}
	}
	
	public static Fireball move(Fireball fb) {
		int r = fb.r;
		int c = fb.c;
		int m = fb.m;
		int s = fb.s;
		int d = fb.d;
		
		int newR = r;
		int newC = c;
		int count = 0;
		
		while (count < s) {
			newR += Dx[d];
			newC += Dy[d];
			
			if (newR > N) newR = 1;
			if (newR < 1) newR = N;
			if (newC > N) newC = 1;
			if (newC < 1) newC = N;
			
			r = newR;
			c = newC;
			count++;
		}
		
		return new Fireball(r, c, m, s, d);
	}
	
	public static void first() {
		Next = new ArrayList<ArrayList<ArrayList<Fireball>>>();
		NextMap = new int[N + 1][N + 1];
		
		for (int i = 0; i <= N; i++) {
			Next.add(new ArrayList<ArrayList<Fireball>>());
			
			for (int j = 0; j <= N; j++) {
				Next.get(i).add(new ArrayList<Fireball>());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (Map[i][j] > 0) {
					for (int k = 0; k < Map[i][j]; k++) {
						Fireball fb = Current.get(i).get(j).get(k);
						Fireball newFb = move(fb);
						
						Next.get(newFb.r).get(newFb.c).add(newFb);
						NextMap[newFb.r][newFb.c] += 1;
					}
				}
			}
		}
		
		Map = NextMap;
		Current = Next;
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		Map = new int[N + 1][N + 1]; 	// 1 ~ N
		Current = new ArrayList<ArrayList<ArrayList<Fireball>>>();
		
		for (int i = 0; i <= N; i++) {
			Current.add(new ArrayList<ArrayList<Fireball>>());
			
			for (int j = 0; j <= N; j++) {
				Current.get(i).add(new ArrayList<Fireball>());
			}
		}
		

		for (int i = 0; i < M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			
			Current.get(r).get(c).add(new Fireball(r, c, m, s, d));
			Map[r][c] += 1;
		}
	}
		
	
	
}

class Fireball {
	int r;
	int c;
	int m;
	int s;
	int d;
	
	public Fireball(int r, int c, int m, int s, int d) {
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}
