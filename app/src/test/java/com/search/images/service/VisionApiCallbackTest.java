package com.search.images.service;

import com.search.images.model.search.SearchResult;

import org.junit.Test;

import retrofit2.Response;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class VisionApiCallbackTest {

    @Test
    public void convertInvalidSearchResponse() {
        //given
        Response<SearchResult> response = mock(Response.class);
        when(response.body()).thenReturn(null);
        SearchApiCallback callback = new SearchApiCallback();

        //when
        SearchResult result = callback.convertResponse(response);

        //then
        assertTrue(result.isInvalid());
    }

    @Test
    public void convertValidSearchResponse() {
        //given
        Response<SearchResult> response = mock(Response.class);
        SearchResult fixture = new SearchResult();
        when(response.body()).thenReturn(fixture);
        SearchApiCallback callback = new SearchApiCallback();

        //when
        SearchResult result = callback.convertResponse(response);

        //then
        assertFalse(result.isInvalid());
    }
}