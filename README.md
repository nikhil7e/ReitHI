# ReitHÍ

ReitHÍ is a website that enables students to make better informed decisions about the courses they choose to enroll in. It gives users the opportunity to rate, review and comment on courses that are taught in the University of Iceland.

**A hosted demo is available on Railway to try out, deployed from the main branch:**

* https://reithi-production.up.railway.app/

## File format:
The file format for the website can roughly be described with the following bullet points:
* `/java`, contains the all of our java code.
  * `/Controllers`, contains all of our controllers that controll the websites behaviour.  
  * `/Persistence`, contains all entities and repositories.
    * `/Entities`, contains all of the entities used by the code, for example, Review, Rating, Comment, User, Course, Professor
    * `/Repositories`, contains all repositories used by the software. They fetch data from the database and hand them to the services.
  * `/Services`, contains all services and service implementations.
* `/resources`, contains all of our HTML and javaScript code, along with all of the extra material such as images and font files. It also includes the applications.properties file that includes the applications configurations.

## Required setup to run the project:
The available version shown above uses a database hosted on SupaBase. The database contains a table corresponding to each repository and when setting up the database, please make sure to allow for null values since the data can be patchy. The data is scraped from https://www.hi.is/ using Web Scraper, but requires quite a bit of data massage since the course pages can vary a lot.
## Additional information:
* General rules for using the ReitHÍ website:
    - Be respectful when leaving comments on courses.
    - Don't use the same passwords for your account you use anywhere else.
    - Don't mention individual teachers when rating a course.
* Project slides: https://docs.google.com/presentation/d/1wWzlPHRd_cZyvKImyCqe1Qb6P2rv_dPvhxVXh1HGxW0/edit?usp=sharing
