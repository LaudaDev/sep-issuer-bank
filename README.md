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
		    "acquirerInfo": {
		        "orderId": 1,
		        "timestamp": "25.01.2016 17:36:33"
		    },
		    "issuerInfo": {
		        "orderId": 13,
		        "timestamp": "28.01.2016 23:52:53"
		    },
		    "transactionStatus": {
		        "code": "00",
		        "message": "TRANSACTION_COMPLETED"
		    }
	    }

	  error (list of error codes is available at: https://github.com/LaudaDev/sep-pcc):

	     {
		    "transactionStatus": {
		        "code": "04",
		        "message": "REQUEST_FORMAT_ERROR"
		}


#How to start project

- clone development branch from github
	git clone -b development https://github.com/LaudaDev/sep-issuer-bank.git

- in eclipse import project as existing maven project

- right click on project -> team -> disconnect

- right click on src folder -> build path -> use as source folder

- right click on project -> run as -> maven build and set goals on "clean install"

- run as -> java application (select application and click ok)

- in case of Error: Could not find or load main class app.Application error, go to project -> clean and run again
