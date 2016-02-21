# Description
POC of an JEE web filter in order to protect an document upload application feature against "malicious" document submission.

# Status

**Under active implementation**

# Note about dependencies not present into Maven repositories

Before to launch a build or execution of the POC, use the script **install-non-maven-deps.bat** to install dependencies into your local Maven cache.

# Run the POC

Use maven command `mvn -DskipTests tomcat7:run-war`

# Context

In web application, when we expect working document coming from format Microsoft Office or PDF from our users, we can expose our application to submission of document that we can categorize as "malicious".

We use the term "malicious" here to refer to document that embed "malicious code" that will be executed when a user will open the document with the associated reader (Word, Excel, Acrobat Reader...).

Usually, when an application expect user to upload a document, the application expect to receive a simple document for which the intended use will be for reading/printing/archiving. The document should not alter is content at opening time.

# Goal

Based on this context, the goal here is to detect when a document contains "code" and then block upload process.

# Format supported

We will focus our work on the following format because it's the format that are often used as attack vector and also to transmit legit information: 

* Microsoft Word document (from 97 to 2013),

* Microsoft Excel document (from 97 to 2013),

* Adobe PDF document.

# Note on Word/Excel API

The reason why Aspose API have been used into this POC are the following:

* There many way to embed Macro into a Microsoft Office document and instead of manually support all the way that exists on the wild (they evolve every days), we prefer to use feature from a company that perform R&D on these format, precisely DOC and XLS native format that are proprietary. 

* The open source API POI for DOC native format is not very stable.

* The open source API JEXCELAPI for XLS native format is not often maintained (last publishing on Maven repository date from 27-Nov-2012).

* Trial version of the APIs can be used for detection only and it's seems that there not license limitation about this type of specific usage (see links and don't hesitate to ping me if i'm wrong  :smiley: ).


# Links

* http://www.aspose.com/community/forums/thread/335727/what-are-the-trial-limitation-of-aspose.words.net.aspx

* http://www.aspose.com/docs/display/wordsreportingservices/Evaluation+Version+Limitations
