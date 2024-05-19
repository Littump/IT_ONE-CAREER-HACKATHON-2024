import { ReactNode, useEffect } from "react";
import Navbar from "@/modules/Navbar";
import Footer from "@/modules/Footer";
import { useNavigate } from "react-router-dom";

interface Props {
  children: ReactNode;
}

export default function Layout({ children }: Props) {
  const token = localStorage.getItem("token");
  const navigate = useNavigate();
  useEffect(() => {
    if (!token) navigate("/intro");
  }, [token]);
  return (
    <>
      <main className="w-full flex flex-col justify-between pt-14 pb-10 min-h-[105vh]">
        <Navbar></Navbar>
        <div className="pt-4 px-6 pb-8 w-full lg:w-[70vw] mx-auto">
          {children}
        </div>
        <Footer></Footer>
      </main>
    </>
  );
}
