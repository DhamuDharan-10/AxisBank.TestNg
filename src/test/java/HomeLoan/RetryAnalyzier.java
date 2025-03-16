package HomeLoan;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzier implements IRetryAnalyzer{
     int i = 1; int j = 2;
     @Override
	public boolean retry(ITestResult result) {
		if (i < j ) {
			i++;
		return true;
	}
		return false;

}
}