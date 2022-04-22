class Main {
	public static int N;
	public static int Max = -(int)1e9;
	public static int Min = (int)1e9;
	public static int[] operator;
	public static int[] operand;
	public static int[] sequence;
	
	public static void DFS(int target, int depth) {
		
		if (depth == target) {
			int x = operand[0];
			for (int i = 1; i < N; i++) {
				int nx = operand[i];
				
				// +
				if (sequence[i - 1] == 0) {
					x += nx;
				}
				
				// -
				if (sequence[i - 1] == 1) {
					x -= nx;
				}
				
				// x
				if (sequence[i - 1] == 2) {
					x *= nx;
				}
				
				// /
				if (sequence[i - 1] == 3) {
					if (x < 0) {
						x = (-x) / nx;
						x = -x;
					}
					else {
						x /= nx;
					}
				}
			}
			
			Max = Math.max(Max, x);
			Min = Math.min(Min, x);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				sequence[depth] = i;
				operator[i] -= 1;
				DFS(target, depth + 1);
				operator[i] += 1;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		operand = new int[N];
		operator = new int[4];
		sequence = new int[N - 1];
		
		for (int i = 0; i < N; i++) {
			operand[i] = sc.nextInt();
		}
		
		for (int i = 0; i < 4; i++) {
			operator[i] = sc.nextInt();
		}
		
		DFS(N - 1, 0);
		System.out.println(Max);
		System.out.println(Min);
	}
}