#include <stdio.h>

float oneOverX(float x);

int main(void)
{
	float m = 0.22167;
	printf("%f%",oneOverX(m));
}

float oneOverX(float x)
{
	int i;
	float approx;
	i = * (int *) &x;
	i = ~i + 2129984413;
	approx = * (float *) &i;
	approx = approx * (2 - x * approx);
	return approx;
}
