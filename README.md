This is a multi-module maven project.

booking-main is the parent module.
MovieController.java handles the request for browse theatre and book a movie ticket.

Application Servers in an auto-scaling group will reside behind a load balancer. This will help in achieving high availability
Servers in the auto-scaling group will scale up or scale down as the traffic increases or goes down.

Integrations with theatres can be done with an API.

Payment gateway API's will be integrated at the backend and a callback will be provided to the gateway for forwarding payment success or failure.

REST APIs can be secured using JWT.
Users data in the system should be stored in an encrypted format.
Also, at the infra level WAF (Web Application Firewall) can be implemented for enhanced security.