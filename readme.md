# Object Sampler
***
This utility allows to create an instance of a class and fill it with some values according to sample Context.
It is useful for example when one wants to create and fill a model for testing purpose.

## How it works:

The main class is **org.ykone.object.sampler.samplers.ObjectSampler**
It needs two parameters :
- The class to sample : The class to instantiate and fill its fields. Be careful: The class must have default constructor
- The sample context : A sample context allows to know how to create sample values. For example String length, 
                       how date value must be generated, more important you can provide your own sampler here to use
                       instead of the default one provided in this project. More use cases can be found in test folder.
                       
   
