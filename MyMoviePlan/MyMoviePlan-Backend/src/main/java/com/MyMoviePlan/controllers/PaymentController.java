package com.MyMoviePlan.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MyMoviePlan.model.Order;
import com.MyMoviePlan.model.OrderDetail;
import com.MyMoviePlan.model.PaymentPOJO;
import com.MyMoviePlan.request.AuthorizeRequest;
import com.MyMoviePlan.response.MessageResponse;
import com.MyMoviePlan.response.ReviewResponse;
import com.MyMoviePlan.security.JwtUtils;
import com.MyMoviePlan.services.OrderService;
import com.MyMoviePlan.services.PayService;
import com.MyMoviePlan.services.PaymentServices;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;




@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")
public class PaymentController {
	
	  JwtUtils jwtUtils;
	  @Autowired
	  PaymentServices paymentServices;
	  /*
	  @Autowired 
	  TicketService ticketService;*/
	  @Autowired
	  PayService payService;
	  
	  @Autowired
	  OrderService orderService;	  
	
	  
	
	 @PostMapping("/payments")
	  public  ResponseEntity<?> authorizePayment(@Valid @RequestBody AuthorizeRequest authorizeRequest) {
	    String product = authorizeRequest.getProductName();
	    String subtotal = authorizeRequest.getSubtotal();
	    String shipping = authorizeRequest.getShipping();
	    String tax = authorizeRequest.getTax();
	    String total = authorizeRequest.getTotal();

		
		OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);
		//return ResponseEntity.ok(orderDetail);

		try {
			PaymentServices paymentServices = new PaymentServices();
			String approvalLink = paymentServices.authorizePayment(orderDetail);
			 //return "redirect:"+approvalLink;
			 
			 return ResponseEntity.ok(new MessageResponse(approvalLink));
			
			 
			//return ResponseEntity.created(approvalLink)

			//return ResponseEntity.ok(approvalLink);
					
		} catch (PayPalRESTException ex) {
			//return ResponseEntity.ok(new MessageResponse("errorMessage"+ex.getMessage()));
			//authorizeRequest.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			//authorizeRequest.getRequestDispatcher("error.jsp").forward(request, response);
		}
		//return "redirect:/";
		return ResponseEntity.ok(new MessageResponse("/"));
	   
	  }
	 	    
	    
	    @GetMapping("/reviewpayment")
	    public ResponseEntity<?>  reviewPayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){		
			
			try {
				PaymentServices paymentServices = new PaymentServices();
				Payment payment = paymentServices.getPaymentDetails(paymentId);
				
				PayerInfo payerInfo = payment.getPayer().getPayerInfo();
				Transaction transaction = payment.getTransactions().get(0);
				
				
				//ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
				
				
				
				//String url = "review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
				
				//request.getRequestDispatcher(url).forward(request, response);
				return ResponseEntity.ok(new ReviewResponse(payerInfo.getFirstName(),
						payerInfo.getLastName(),
						payerInfo.getEmail(),
						paymentId,
						payerInfo.getPayerId(),
						transaction.getDescription(),
						transaction.getAmount().getDetails().getSubtotal(),
						transaction.getAmount().getDetails().getTax(),
						transaction.getAmount().getTotal()
						));
				
			} catch (PayPalRESTException ex) {
				//request.setAttribute("errorMessage", ex.getMessage());
				ex.printStackTrace();
				//request.getRequestDispatcher("error.jsp").forward(request, response);
				return ResponseEntity.ok("Not Ok "+ ex.getMessage());
			}	
			
			
		}
	    
	    @GetMapping("/execute_payment")
	    public ResponseEntity<?>  executePayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId)
				throws ServletException, IOException {
			//String paymentId = request.getParameter("paymentId");
			//String payerId = request.getParameter("PayerID");

			try {
				PaymentServices paymentServices = new PaymentServices();
				Payment payment = paymentServices.executePayment(paymentId, payerId);
				
				PayerInfo payerInfo = payment.getPayer().getPayerInfo();
				Transaction transaction = payment.getTransactions().get(0);
				
				//transaction.getTransactions();
				//List<Transaction> trans= new ArrayList<Transaction>();
				
				//transaction.getTransactions().forEach(transac -> trans.));
				
							
				//request.setAttribute("payer", payerInfo);
				//request.setAttribute("transaction", transaction);			

				//request.getRequestDispatcher("receipt.jsp").forward(request, response);
				PaymentPOJO paymentPojo=new PaymentPOJO(
						payerInfo.getFirstName(),
						payerInfo.getLastName(),
						transaction.getDescription(),
						transaction.getAmount().getDetails().getSubtotal(),
						transaction.getAmount().getDetails().getTax(),
						transaction.getAmount().getTotal(),
						payerId,
						paymentId,
						payerInfo.getEmail()
						);
	
				payService.savePay(paymentPojo);
				
				/*return ResponseEntity.ok(new ReceiptResponse(payerInfo.getFirstName(),
						payerInfo.getLastName(),
						transaction.getDescription(),
						transaction.getAmount().getDetails().getSubtotal(),
						transaction.getAmount().getDetails().getTax(),
						transaction.getAmount().getTotal(),
						payerInfo.getEmail(),
						payerId,
						paymentId
						));*/
				
				return ResponseEntity.ok(paymentPojo);
				
			} catch (PayPalRESTException ex) {
				//request.setAttribute("errorMessage", ex.getMessage());
				ex.printStackTrace();
				return ResponseEntity.ok("Not Ok "+ ex.getMessage());
				//request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} 
	    
	    
	    @GetMapping("/cancel")
	    public ResponseEntity<?> cancelPay() {
	        System.out.println("cancel");	        
	        return ResponseEntity.ok(new MessageResponse("/cancel"));
	       //return "cancel";
	    }
	    
	    @GetMapping("/listOrders")
	    public ResponseEntity<?> listOrder() {

	    	List<Order> order=orderService.listOfOrders();
	        return ResponseEntity.ok(order);
	       //return "cancel";
	    }
	
	    
		@GetMapping("/getOrderDetails/{id}")
		private ResponseEntity<?> getOrder(@PathVariable("id")Integer id) {
			List<Order> order=orderService.getOrderDetails(id);
			return ResponseEntity.ok(order);
		}  	  
		
		
		 @GetMapping("/listPayDetails")
		    public ResponseEntity<?> listPaymentDetails() {

		    	List<PaymentPOJO> payDetails=payService.listPay();
		        return ResponseEntity.ok(payDetails);
		       //return "cancel";
		    }
		 
			@GetMapping("/getPayDetails/{id}")
			private ResponseEntity<?> getPayDetails(@PathVariable("id")String id) {
				PaymentPOJO payDetails=payService.getPayDetails(id);
				return ResponseEntity.ok(payDetails);
			}  	  
			
	

}
