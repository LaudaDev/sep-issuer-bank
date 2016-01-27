# sep-issuer-bank
SEP 2015 - Issuer Bank
REST service for authentication and authorization credit cards
Spring Boot & MongoDB


Available methods:
  bank/checkcard
  
  Input message format (example):
    {
      "cardInfo": {
         "pan": "1000100010001001",
         "securityCode": "101",
         "holderName": "Card 11",
         "expirationDate": "11/17"
      },
      "acquirerInfo": {
         "orderId": "1",
         "timestamp": "25.01.2016 17:36:11"
      },
      "transactionAmount": "100"
    }
    
  Output message format (example):
    success:
      {
        "acquirerInfo": {
            "orderId": 1,
            "timestamp": "25.01.2016 17:36:11"
        },
        "issuerInfo": {
            "orderId": 8,
            "timestamp": "27.01.2016 18:19:47"
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
      }

