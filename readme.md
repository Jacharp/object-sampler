# Object Sampler
***
This utility allows to create an instance of a class ad fill it with some value according to saample Context.
It is useful for example when one wants to create and fill a model for testing purpose.

## How it works:

The main class is **org.ykone.object.sampler.samplers.ObjectSampler**
It needs two parameters :
- The class to sample : The class to instanciate and fill its fields. Be careful: The class must have default constructor
- The sample context : A sample context allows to know how to create sample values. For example String length, 
                       how date value must be generated, more important you can provide your own sampler here to use
                       instead of the default one provided in this project. Let see an example
                       
   
