https://www.techgeeknext.com/spring-boot/method-level-custom-annotation
https://www.jmdoudoux.fr/java/dej/chap-aop.htm#:~:text=L%27AOP%20est%20un%20type,mais%20ne%20le%20remplace%20pas.
https://experienceleague.adobe.com/docs/contributor/contributor-guide/writing-essentials/markdown.html?lang=fr
https://github.com/danielangel22/sample-spring-annotations/blob/master/src/main/java/com/example/aop/ManagementAnnotations.java


**N'oublie pas la partie (AOP) Aspect Oriented Programming**


# Pour la partie Annotation
## @Target
It specifies where the annotation should be applied. Because it is at the method level in our case, we pass 
**ElementType.METHOD** as a parameter.
## @Retention
It specifies when to use this annotation, which in our case is at runtime(lors de l'excution).

# Partie Aspect (AOP)
#### @Aspect
It specifies that class is an Aspect Class.
#### @Component
It specifies that the class is a Spring bean.
#### authorize method
Create method with any name.
####  Around() with @annotation
Pass the annotation name (that you created using Interface) inside the @Around annotation, so that Spring understands that the logic around this annotation needs to be applied.
ProceedingJoinPoint Parameter
The method is accessed via the ProceedingJoinPoint. You can access input parameters received from REST Method from ProceedingJoinPoint.
#### joinPoint.proceed()
This is where the original method is put into action.