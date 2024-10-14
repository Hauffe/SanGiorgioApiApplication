# 📁 San Giorgio API

This Spring boot application is responsible for processing payments using an MQS (RabbitMQ) system.

# 🛠️ How to run

Please follow the instructions to run this application:

* Used Maven 3.9.1 and JDK 21

Maven build:

        mvn clean
        mvn install

To run the integration tests alone, please run:

        mvn install -DskipITs=false

Go to the `\desafio\target` directory and find the `desafio-0.0.1-SNAPSHOT.jar` file built, then run:

        java -jar .\desafio-0.0.1-SNAPSHOT.jar


Go to the `desafio\src\main\resources\docker-compose.yml` directory and find the `docker-compose.yml` file, then run:

        docker compose docker-compose.yml


Check `http://localhost:15672/#/` to see RabbitMQ running, create three queues according to the code bellow:

    public void sendToQueue(PaymentItem item) {
        switch (item.getPaymentStatus()) {
            case PARTIAL -> producer.send("partial.payment.queue", item);
            case TOTAL -> producer.send("total.payment.queue", item);
            case EXCESS -> producer.send("excess.payment.queue", item);
        }
    }

![image](https://github.com/user-attachments/assets/32347619-c878-403a-9510-ca1afb2ec60e)


Now, if everithing works, test by sending the following request to  `/api/payment` endpoint at:

        PUT http://localhost:8080/api/payment

        Body:
        {
            "client_id": 1,
            "payment_items":[
                {
                    "payment_id": 1,
                    "payment_value": 880.0
                }
            ]
        }

You may see the queues being load with the requests

![image](https://github.com/user-attachments/assets/3b9bf7ac-9351-4539-b184-0cafaf9284b2)


#  How it works

### Endpoints:

Please use Postman to better execute and test this application, this is also necessary to facilitate uploading documents as the following image shows:


* ### Upload csv file and load the data

        PUT: http://localhost:8080/api/payment

    * #### Request body
            {
                    "client_id": 1,
                    "payment_items":[
                        {
                            "payment_id": 1,
                            "payment_value": 880.0
                        }
                    ]
            }

* ### Get all films

        GET:   http://localhost:8080/raspberry/films

  No request body needed, returns all the films loaded.


* ### Get the min and max values from the winner producer by the years

        GET:  http://localhost:8080/raspberry/winners

  No request body needed, return the minimum and maximum time between years of award by producers

* ### RabbitMQ URL:

  Location to the rabbitMQ instance

        http://localhost:15672/#

* ### H2 URL to access database if needed

        http://localhost:8080/h2-console/

# Final considerations

This is the base structure to handle MQS processing, having both reading and sending methods to a queue using RabbitMQ.
