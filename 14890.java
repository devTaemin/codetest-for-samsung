class Main {
	public static int N, L, count;
	public static int[][] map;
	public static boolean[] slope;
	
	public static void Row() {
		for (int i = 1; i <= N; i++) {
			int cur = map[i][1];
			int col = 1;
			boolean street = true;
			slope = new boolean[N + 1]; // 1 ~ N
			
			for (int j = 2; j <= N; j++) {
				int next = map[i][j];
				
				if (cur == next) {
					cur = next;
					col = j;
					continue;
				}
				
				if (Math.abs(cur - next) > 1) {
					street = false;
					break;
				}
				
				// Ascending
				if (cur < next) {
					int from = col - L + 1;
					int to = col;
					
					// check range
					if (from < 1 || from > N || to < 1 || to > N) {
						street = false;
						break;
					}
					
					// check value
					boolean isTrue = true;
					for (int k = from; k <= to; k++) {
						if (map[i][k] != cur || slope[k] == true) {
							isTrue = false;
							break;
						}
						slope[k] = true;
					}
					
					if (!isTrue) {
						street = false;
						break;
					}
					else {
						cur = next;
						col = j;
					}
				}
				
				// Descending
				if (cur > next) {
					int from = j;
					int to = j + L - 1;
					
					// Check range
					if (from < 1 || from > N || to < 1 || to > N) {
						street = false;
						break;
					}
					
					// Check value
					boolean isTrue = true;
					for (int k = from; k <= to; k++) {
						if (map[i][k] != next || slope[k] == true) {
							isTrue = false;
							break;
						}
						slope[k] = true;
					}
					
					if (!isTrue) {
						street = false;
						break;
					}
					else {
						cur = next;
						col = to;
						j = to;
					}
				}
				
			}
			
			if (street) {
				count++;
				//System.out.println(i);
			}
			
		}
	}
	
	public static void Col() {
		for (int i = 1; i <= N; i++) {
			int cur = map[1][i];
			int row = 1;
			boolean street = true;
			slope = new boolean[N + 1]; // 1 ~ N
			
			for (int j = 2; j <= N; j++) {
				int next = map[j][i];
				
				if (cur == next) {
					cur = next;
					row = j;
					continue;
				}
				
				if (Math.abs(cur - next) > 1) {
					street = false;
					break;
				}
				
				// Ascending
				if (cur < next) {
					int from = row - L + 1;
					int to = row;
					
					// check range
					if (from < 1 || from > N || to < 1 || to > N) {
						street = false;
						break;
					}
					
					// check value
					boolean isTrue = true;
					for (int k = from; k <= to; k++) {
						if (map[k][i] != cur || slope[k] == true) {
							isTrue = false;
							break;
						}
						slope[k] = true;
					}
					
					if (!isTrue) {
						street = false;
						break;
					}
					else {
						cur = next;
						row = j;
					}
				}
				
				// Descending
				if (cur > next) {
					int from = j;
					int to = j + L - 1;
					
					// Check range
					if (from < 1 || from > N || to < 1 || to > N) {
						street = false;
						break;
					}
					
					// Check value
					boolean isTrue = true;
					for (int k = from; k <= to; k++) {
						if (map[k][i] != next || slope[k] == true) {
							isTrue = false;
							break;
						}
						slope[k] = true;
					}
					
					if (!isTrue) {
						street = false;
						break;
					}
					else {
						cur = next;
						row = to;
						j = to;
					}
				}
				
			}
			
			if (street) {
				count++;
				//System.out.println(i);
			}
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		map = new int[N + 1][N + 1]; // 1 ~ N
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		Row();
		Col();
		System.out.println(count);
		sc.close();
	}
}