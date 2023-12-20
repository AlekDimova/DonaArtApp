package org.softuni.DonaArtApp.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* org.softuni.DonaArtApp.service.ArtService.getAllArts(..))")
    public void trackOfferSearch(){}

    @Pointcut("@annotation(WarnIfExecutionExceeds)")
    public void warnIfExecutionExceeds(){}
}
