package registrationflows;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.SuperTestScript;
import generics.AddDate;
import generics.Property;
import pages.FamilySubscription;
import pages.HomePage;
import pages.KontoDetails;
import pages.OrderDetails;
import pages.PaymentPage;
import pages.RegistrationPage;
import pages.SubscriptionDetails;
import pages.SubscriptionPage;
import pages.UpdateCardDetails;
import pages.UserDetails;

public class FreeTrialFlow_DE extends SuperTestScript{
	
	public static String newEmail;
	public static String confirm;
	public static String newPwd;
	public static String CardNumber;
	public static String cvc;
	SimpleDateFormat sd = new SimpleDateFormat("ddMMMyy_HHmmss"); 
	
		
	@Test(enabled = true,priority = 1, groups = {"FreeTrialFlow_DE" },dataProvider="userData_DE",
			dataProviderClass=common.DataProviderComponent.class)
	public void FreeTrialFlow_Positive(String subs,String email,String password,String gateway,String CardNumber,String cvc,String month,String year,
			String bankName,String personnum) {
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		FamilySubscription fam = new FamilySubscription(driver);
		Reporter.log("Registration for FreeTrailFlow in Swedish Country site Starts......");
		log.info("Validating the FreeTrailPeriod for 14 Days.....");
		Assert.assertTrue(sub.getTrailDate().trim().contains(AddDate.addingDays(14,"DE").toString().trim()));
		log.info("Validation of FreeTrailPeriod is completed.");
		if(subs.equals("Silber")) {
			Reporter.log("Free Trail Flow For "+subs+"Subscription Starts........");
			log.info("FreeTrialFlow Continues for Subscription"+subs+"........");
			sub.clickforSilver();
			log.info("Validating the Subscription Price.....");
			log.info(subs+":"+sub.getSubsciptionPrice().trim());
			Assert.assertEquals(sub.getSubsciptionPrice().trim(), "13.99 \u20ac");
			log.info("Validation of Subscription Price is Completed.");
			log.info("Validating the allowed number of profiles for the subscription....");
			log.info("Profiles allowed for "+subs+":"+sub.getProfileNum().trim());
			Assert.assertEquals(sub.getProfileNum().trim(), "1");
			log.info("Validation of max allowed profiles is completed");
			sub.clickToContinue();
		}else if(subs.equals("Gold")) {
			Reporter.log("Free Trail Flow For "+subs+"Subscription Starts........");
			log.info("FreeTrialFlow Continues for Subscription"+subs+"........");
			sub.clickforGold();
			log.info("Validating the Subscription Price.....");
			log.info(subs+":"+sub.getSubsciptionPrice().trim());
			Assert.assertEquals(sub.getSubsciptionPrice().trim(), "16.99 \u20ac");
			log.info("Validation of Subscription Price is Completed.");
			log.info("Validating the allowed number of profiles for the subscription....");
			log.info("Profiles allowed for "+subs+":"+sub.getProfileNum().trim());
			Assert.assertEquals(sub.getProfileNum().trim(), "1");
			log.info("Validation of max allowed profiles is completed");
			sub.clickToContinue();
		}else {
			
			if(subs.equals("Familie 2")) {
				Reporter.log("Free Trail Flow For "+subs+"Subscription Starts.....");
				log.info("FreeTrialFlow Continues for Subscription"+subs+"......");
				sub.clickforFamily();
				Assert.assertEquals(sub.getProfileNum().trim(), "2-4");
				sub.clickToContinue();
				fam.clickforFamily2();
				log.info("Validation of number of profiles for "+subs+" subscription.......");
				log.info("No. of profiles for "+subs+": "+fam.getFamily2Text(0));
				log.info("Validation of max allowed profiles is completed");
				log.info("Validation of subscription price starts.....");
				log.info("Subscription price for "+subs+":"+fam.getFamily2Text(2));
				Assert.assertTrue(fam.getFamily2Text(2).trim().contains("19.99 \u20ac"));
				log.info("Validation of Subscription Price is completed.");
				fam.clickToContinue();
			}else if(subs.equals("Familie 3")) {
				Reporter.log("Free Trail Flow For "+subs+"Subscription Starts.....");
				log.info("FreeTrialFlow Continues for Subscription"+subs+"......");
				sub.clickforFamily();
				Assert.assertEquals(sub.getProfileNum().trim(), "2-4");
				sub.clickToContinue();
				fam.clickforFamily3();
				log.info("Validation of number of profiles for "+subs+" subscription.......");
				log.info("No. of profiles for "+subs+": "+fam.getFamily3Text(0));
				log.info("Validation of max allowed profiles is completed");
				log.info("Validation of subscription price starts.....");
				log.info("Subscription price for "+subs+":"+fam.getFamily3Text(2));
				Assert.assertTrue(fam.getFamily3Text(2).trim().contains("23.99 \u20ac"));
				log.info("Validation of Subscription Price is completed.");
				fam.clickToContinue();
			}else {
				Reporter.log("Free Trail Flow For "+subs+"Subscription Starts.....");
				log.info("FreeTrialFlow Continues for Subscription"+subs+"......");
				sub.clickforFamily();
				Assert.assertEquals(sub.getProfileNum().trim(), "2-4");
				sub.clickToContinue();
				fam.clickforFamily4();
				log.info("Validation of number of profiles for "+subs+" subscription.......");
				log.info("No. of profiles for "+subs+": "+fam.getFamily4Text(0));
				log.info("Validation of max allowed profiles is completed");
				log.info("Validation of subscription price starts.....");
				log.info("Subscription price for "+subs+":"+fam.getFamily4Text(2));
				Assert.assertTrue(fam.getFamily4Text(2).trim().contains("27.99 \u20ac"));
				log.info("Validation of Subscription Price is completed.");
				fam.clickToContinue();
			}
		}
		
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(email);
		reg.confirmMail(email);
		reg.enterPassword(password);
		//reg.agreeTerms();
		reg.clickToContinue();
		
		Reporter.log("Payment Flow Starts for "+subs+"subscription....");
		log.info("Payment Starts.....");
		PaymentPage pay = new PaymentPage(driver);
		if(gateway.equals("Adyen")) {
			
			pay.adyenDropDown();
			log.info("Validation of payment Summary text in "+gateway);
			if(subs.equals("Silber")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Silber"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("13.99 \u20ac"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
			}else if(subs.equals("Gold")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Gold"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("16.99 \u20ac"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
			}else {
				if(subs.equals("Familie 2")) {
					log.info(pay.getPaymentSummary(0).trim());
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 2 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("19.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}else if(subs.equals("Familie 3")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 3 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("23.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}else {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 4 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("27.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}
			}
			
			WebDriverWait wait = new WebDriverWait(driver,40);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			pay.enterCardNumber(CardNumber);
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
			pay.enterexpiryDate(month+year);
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
			pay.entercvv(cvc);
			driver.switchTo().defaultContent();
			pay.clickOnSubmit();
		}else if(gateway.equals("Sofort")) {
			
			pay.sofortDropDown();
			log.info("Validation of payment summary text in" +gateway);
			if(subs.equals("Silber")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Silber"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("13.99 \u20ac"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
			}else if(subs.equals("Gold")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Gold"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("16.99 \u20ac"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
			}else {
				if(subs.equals("Familie 2")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 2 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("19.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}else if(subs.equals("Familie 3")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 3 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("23.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}else {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 4 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("27.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}
			}
			pay.clickOnSubmit();
			pay.selectBankSofort(bankName);
			pay.enterBankName(personnum);
			pay.sofortSubmitSearch();
			pay.sofortEnterUserId("12345");
			pay.sofortEnterPin("1234");
			pay.sofortSubmitSearch();
			pay.sofortSelectRadio();
			pay.sofortSubmitSearch();
			pay.sofortEnterTan("12345");
			pay.sofortTanCheckBox();
			pay.sofortSubmitSearch();
		}else {
			
			pay.giropayDropDown();
			if(subs.equals("Silber")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Silber"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("13.99 \u20ac"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
			}else if(subs.equals("Gold")) {
				Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Gold"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("16.99 \u20ac"));
				Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
				Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
			}else {
				if(subs.equals("Familie 2")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 2 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("19.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}else if(subs.equals("Familie 3")) {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 3 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("23.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}else {
					Assert.assertTrue(pay.getPaymentSummary(0).trim().contains("Familie, 4 Anwender"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains("27.99 \u20ac"));
					Assert.assertTrue(pay.getPaymentSummary(2).trim().contains("14"));
					Assert.assertTrue(pay.getPaymentSummary(1).trim().contains(AddDate.addingDays(14, "DE").toString().trim()));
				}
			}
			
			pay.clickOnSubmit();
			pay.giropayEnterBankName("test");
			pay.giropayBankSelection(bankName);
			pay.giropayClickMainSubmit();
			String[] s = pay.giropay_statusCodes("Success").split(";");
			String sc = s[0].toString();
			String exsc = s[1].toString();
			pay.giropay_enterSC(sc);
			pay.giropay_enterexSC(exsc);
			pay.giropay_enterCName("test");
			pay.giropay_enterCIBAN(personnum);
			pay.giropay_conf_submit();
			
		}
		
		Reporter.log("Payment flow is Completed for" +subs);
		log.info("Entry of first Name and last Name by the user");
		UserDetails ud = new UserDetails(driver);
		ud.enterFirstName();
		ud.enterLastname();
		ud.enterPhoneNumber();
		ud.clickToContinue();
		ud.clickOnMittKonto();
		
		Reporter.log("Validation of Konto Details Started...");
		KontoDetails konto = new KontoDetails(driver);
		konto.clickForKonto();
		//String mail = konto.getEmail();
		log.info("Validation of email...");
		Assert.assertEquals(konto.getEmail().trim(),email);
		//konto.setEmail("testmar1802@test.se");
		log.info("Validation of email Details completed");
		konto.setFirstName("test");
		konto.setLastName("test");
		konto.setAddress("Marathalli");
		konto.setPhNo("9846584659");
		konto.setzipCode("560043");
		konto.setCity("Bangalore");
		konto.clickToSave();
		
		home.clickToSearch();
		
		SubscriptionDetails suba = new SubscriptionDetails(driver);
		suba.clickToGetSubDetails();
		log.info("Validation of Subscription in Konto....");
		Assert.assertTrue(suba.getSubscriptionDetails().trim().contains(subs.trim()));
		log.info("Validation of NextRunDate in Konto");
		Assert.assertTrue(suba.getAccountText().trim().contains(AddDate.addingDays(14,"DE").toString().trim()));
		
		home.clickToSearch();
		
		UpdateCardDetails card = new UpdateCardDetails(driver);
		card.clickToGetCardDetails();
		Assert.assertTrue(suba.getAccountText().trim().contains(AddDate.addingDays(14,"DE").toString().trim()));
		
		home.clickToSearch();
		
		OrderDetails order = new OrderDetails(driver);
		order.clickTogetOrderDetails();
		order.clickOnFakutra();
		order.GoBackOrderHist();
		
		home.clickToSearch();
		konto.clickToLogOut();
	}
	
	@Test(enabled = true,priority = 2,groups= {"FreeTrialNegative_DE"})
	public void FreeTrailFlowDE_RegistrationPage_EmptyEmailPassword() {
		
		newEmail = "";
		confirm = "";
		newPwd = "";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.clickToContinue();
		
		log.info("Validating the error for Empty Email.....");
		String expected_EmptyEmail = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "empty_email");
		Assert.assertEquals(reg.getErrorEmptyEmail().trim(), expected_EmptyEmail.trim());
		log.info("Error is displayed correctly");
		
		log.info("Validating the error for Empty Confirm Email.....");
		String expected_Emptyconfirm = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "empty_confirm_email");
		Assert.assertEquals(reg.getErrorEmptyConfirm().trim(), expected_Emptyconfirm);
		log.info("Error is displayed correctly");
		
		log.info("Validating the error for Empty Password.....");
		String expected_Emptypass = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "empty_password");
		Assert.assertEquals(reg.getErrorEmptyPassword().trim(), expected_Emptypass);
		log.info("Error is displayed correctly");
				
	}
	

	@Test(enabled = true,priority = 4,groups = {"FreeTrialNegative_DE"})
	public void FreeTrailFlowDE_wrongEmail() {
		
		newEmail = "testreg_wrongemail@test.de";
		confirm = "testreg_wrongemail1@test.de";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.clickToContinue();
		
		log.info("Validating the error for wrong confirm email.....");
		String Expected_Error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "wrong_email");
		Assert.assertEquals(reg.getErrorEmptyConfirm(), Expected_Error);
		log.info("Error is displayed properly");
	}
	
	@Test(enabled = true,priority = 5,groups = {"FreeTrialNegative_DE"})
	public void FreeTrailFlowDE_Registration_InvalidPassword() {
		
		newEmail = "testreg_invalidpass@test.de";
		confirm = "testreg_invalidpass@test.de";
		newPwd = "pas";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		reg.clickToContinue();
		
		log.info("Validating the error for password invalid....");
		String Expected_Error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "invalid_password");
		Assert.assertEquals(reg.getErrorEmptyPassword().trim(), Expected_Error.trim());
		log.info("Error is displayed correctly");
	}
	
	@Test(enabled = true,priority = 6,groups = {"FreeTrialNegative_DE"})
	public void FreeTrailFlowDE_invalidCardNumber() {
		
		newEmail = "testadyen_invalidcard@test.de";
		confirm = "testadyen_invalidcard@test.de";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		//reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropDown();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.enterCardNumber("5555 4857 5948 8675");
		driver.switchTo().defaultContent();
		pay.clickOnSubmit();
		
		log.info("Validating the error for invalid card number Adyen.....");
		String Expected_Error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "invalid_card_num");
		Assert.assertEquals(pay.getAdyenInvalidCardError().trim(), Expected_Error);
		log.info("Error is displayed correctly");
	
	}
	
	@Test(enabled=true,priority=7,groups = {"FreeTrialNegative_DE"})
	public void FreeTrialFlow_InvalidCardExpiry() {
		
		newEmail = "testadyen_invalidexpiry@test.de";
		confirm = "testadyen_invalidexpiry@test.de";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		//reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropDown();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.enterCardNumber("5555 4444 3333 1111");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		pay.enterexpiryDate("0517");
		driver.switchTo().defaultContent();
		pay.clickOnSubmit();
		
		log.info("Validating the error for wrong expiry date....");
		String Expected_error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "empty_month");
		Assert.assertEquals(pay.getAdyenInvalidExpiryError().trim(), Expected_error.trim());
		log.info("Error is displayed correctly");
	}
	
	@Test(enabled=true,priority=8,groups = {"FreeTrialNegative_DE"})
	public void FreeTrialFlow_InvalidCvv() {
		
		newEmail = "testadyen_invalidexpiry@test.de";
		confirm = "testadyen_invalidexpiry@test.de";
		newPwd = "password";
		
		HomePage home = new HomePage(driver);
		home.clickToacceptCookies();
		home.clickToRegister();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		SubscriptionPage sub = new SubscriptionPage(driver);
		sub.clickforGold();
		sub.clickToContinue();
		driver.findElement(By.xpath("//a[@class='btn btn--primary btn--arrow']")).click();
		
		RegistrationPage reg = new RegistrationPage(driver);
		reg.enterEmail(newEmail);
		reg.confirmMail(confirm);
		reg.enterPassword(newPwd);
		//reg.agreeTerms();
		reg.clickToContinue();
		
		PaymentPage pay = new PaymentPage(driver);
		pay.adyenDropDown();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		pay.enterCardNumber("5555 4444 3333 1111");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		pay.enterexpiryDate("1020");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
		pay.entercvv("7869");
		driver.switchTo().defaultContent();
		pay.clickOnSubmit();
		
		log.info("Validating the error for invalid cvv Adyen....");
		String Expected_error = Property.getPropertyValue(CONFIG_PATH+ERROR_FILE_DE, "invalid_cvv");
		Assert.assertEquals(pay.getAdyenInvalidcvvError().trim(), Expected_error.trim());
		log.info("Error is displayed correctly");
		
	}

}
