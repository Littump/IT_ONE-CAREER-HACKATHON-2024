import { ReactNode } from "react";
import Navbar from "@/modules/Navbar";
import Footer from "@/modules/Footer";

interface Props {
  children: ReactNode;
}

export default function Layout({ children }: Props) {
  return (
    <>
      <main className="w-full flex flex-col justify-between pt-14 pb-10 min-h-[100vh]">
        <Navbar></Navbar>
        <div className="pt-4 px-6 pb-8 lg:w-[70vw] mx-auto">{children}</div>
        <Footer></Footer>
      </main>
    </>
  );
}
