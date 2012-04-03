package com.supinfo.notetonsta.xml.converter;

public interface XmlConverter<X, T> {
	X convertToXml(T obj);
	T convertFromXml(X xmlObj);
}
