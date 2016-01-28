use issuer
db.creditCard.drop();
db.transaction.drop();
db.checkCardRequest.drop();
db.createCollection("creditCard");
db.createCollection("transaction");
db.createCollection("checkCardRequest");

db.creditCard.insert({  
   "_id":NumberInt(1),
   "pan":"1111111111111111",
   "securityCode":NumberInt(111),
   "holderName":"Card 1",
   "expirationDate":"11/17",
   "amount":"150000.44"
});

db.creditCard.insert({  
   "_id":NumberInt(2),
   "pan":"2222222222222222",
   "securityCode":NumberInt(222),
   "holderName":"Card 2",
   "expirationDate":"10/16",
   "amount":"100000"
});

db.creditCard.insert({  
   "_id":NumberInt(3),
   "pan":"3333333333333333",
   "securityCode":NumberInt(333),
   "holderName":"Card 3",
   "expirationDate":"04/15",
   "amount":"47000.5"
});

db.creditCard.insert({  
   "_id":NumberInt(4),
   "pan":"4444444444444444",
   "securityCode":NumberInt(444),
   "holderName":"Card 4",
   "expirationDate":"07/20",
   "amount":"570500"
});

db.creditCard.insert({  
   "_id":NumberInt(5),
   "pan":"5555555555555555",
   "securityCode":NumberInt(555),
   "holderName":"Card 5",
   "expirationDate":"07/19",
   "amount":"245000"
});

