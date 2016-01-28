#SEP 2015 - Issuer Bank

REST service for authentication and authorization credit cards

Spring Boot & MongoDB

Available methods:

	  api/issuer/auth
	  
	  Input message format (example):
	  
	    {
		    "cardInfo": {
		       "pan": "1111111111111111",
		       "securityCode": "111",
		       "holderName": "Card 1",
		       "expirationDate": "11/17"
		    },
		 
		    "acquirerInfo": {
		       "orderId": "1",
		       "timestamp": "25.01.2016 17:36:33"
		    },
		 
		    "transactionAmount": "7500"
		}
	    
	  Output message format (example):
	    success:
	    
	    {
		    "cardInfo": {
		       "pan": "1111111111111111",
		       "securityCode": "111",
		       "holderName": "Card 1",
		       "expirationDate": "11/17"
		    },
		 
		    "acquirerInfo": {
		       "orderId": "1",
		       "timestamp": "25.01.2016 17:36:33"
		    },
		 
		    "transactionAmount": "7500"
		}
	    
	  error (list of error codes is available at: https://github.com/LaudaDev/sep-pcc):
	  
	     {
		    "transactionStatus": {
		        "code": "04",
		        "message": "REQUEST_FORMAT_ERROR"
		  }
