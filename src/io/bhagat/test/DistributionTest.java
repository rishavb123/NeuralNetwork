package io.bhagat.test;

import io.bhagat.math.statistics.NormalDistribution;

public class DistributionTest {

	public static void main(String[] args)
	{
		NormalDistribution distribution = new NormalDistribution(0, 1);
		System.out.println(distribution.probability(0.0));
		System.out.println(distribution.probability(-1, 1));
		System.out.println(distribution.experimentalProbability(-1, 1, 10000));
	}
}
