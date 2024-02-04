===================================
ReStructuredText Document & Builder
===================================

This project is the library for building reStructuredText document object and serializing it into text representation.

The library is part of the project `RstDoclet for JavaDoc Tool, generating reStructuredText for Sphinx <https://github.com/devives/rst-doclet>`_.

.. contents:: Overview
   :depth: 2

Quick Start
-----------

1. Add ``mavencentral()`` repository to your root ``build.gradle``:

   .. code:: gradle

      repositories {
          mavenCentral()
      }
#. Add library to dependencies:

   .. code:: gradle

      dependencies {
          implementation 'com.devives:devive-rst-document:0.2.0'
      }

Sample code
-----------

.. code:: java

   private String codeSample() {
       return Rst.builders().document()
           .title("Document title", true)
           .paragraph("Simple text without any emphasis or enhancements.")
           .subTitle("Section title")
           .beginParagraph().text("Text can contains emphasis like ").bold("bold")
           .text(" or ").italic("italic").text(" text.").end()
           .build()
           .serialize();
   }

will return

::

   ==============
   Document title
   ==============

   Simple text without any emphasis or enhancements.

   Section title
   -------------

   Text can contains emphasis like **bold** or *italic* text.


License
-------

The code of project distributed under the GNU General Public License version 2 or 
any later version. The source code is available on `GitHub <https://github.com/devives/rst-document>`_.

Links
-----

* `An Introduction to reStructuredText <https://docutils.sourceforge.io/docs/ref/rst/introduction.html>`_
* `reStructuredText Markup Specification <https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html>`_
* `reStructuredText Directives <https://docutils.sourceforge.io/docs/ref/rst/directives.html>`_
* `reStructuredText Interpreted Text Roles <https://docutils.sourceforge.io/docs/ref/rst/roles.html>`_
* `The Docutils Document Tree <https://docutils.sourceforge.io/docs/ref/doctree.html>`_

.. footer::

   This document generated using `this code <https://github.com/devives/rst-document/blob/main/src/test/java/com/devives/rst/ReadMeGenerator.java>`_.
