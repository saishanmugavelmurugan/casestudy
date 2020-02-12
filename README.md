# casestudy
1) Checkout the code 
2) Run as springboot application
3) Hit Inmemory database url:http://localhost:9999/h2-console/login.do
    jdbcurl=jdbc:h2:mem:testdb
    username =sa
    password=password
4)view the account table loaded with data.   
5)Use postman to post the transfer request.
      URl: localhost:9999/sendAmount
      Json request format
      {
      "sourceAccountNumber":"rbs123456",
      "destinationAccountNumber":"rbs123457",
      "amount":10
      }
  6)view all accounts with url localhost:9999/all
  7)add new account details with url localhost:9999/add/{accountnumber}/{amount}
