#### Cities Interview Task

I used a treemap to store the data retrieved from the jason file. Treemap by itself sorts values based on a key, which in this case basicaly the query format. "City, COUNTRYCODE".
Thanks to the treemap, filtering is very fast and easy.

I was getting a OutOfMemory exception when deserializing the json using th org.json lib, so instead I used Gson's streaming capabilities.

Searching is still done on another thread just to be sure the ui is stutter free.
Using rxjava would make things look better, with the schedulers, instead of the runOnUithread call in the Activity.

For testing purposes I made a AssetRetriever abstract, that would have 2 implementations, one for tes one for real app. This was done to get rid of Contexts in presenter and model, so that proper Unit tests could be done.
AssetRetriever is singleton class, that either references a testable or a real implementation. 
The ASsetRetrieverImpl class holds on to Application context, which is rather safe, but for added security the context is wrapped in a weakreference.
The AssetRetrieverTestable is a class that returns hardcoded strings for the test purposes.

I added an installation apk in root as well.
